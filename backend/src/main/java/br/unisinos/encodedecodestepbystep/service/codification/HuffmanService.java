package br.unisinos.encodedecodestepbystep.service.codification;

import br.unisinos.encodedecodestepbystep.domain.Codification;
import br.unisinos.encodedecodestepbystep.repository.ReaderInterface;
import br.unisinos.encodedecodestepbystep.repository.WriterInterface;
import br.unisinos.encodedecodestepbystep.utils.StringUtils;
import br.unisinos.encodedecodestepbystep.utils.exceptions.WrongFormatExpection;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
public class HuffmanService implements CodificationService {

    @Override
    public void encode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        Codification.setCodificationName("Huffman Estático");
        writer.writeSemHamming(getBitsIdentificacaoAlgoritmo(writer));
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Map<Character, Double> probabilityMap = new HashMap<Character, Double>();
        int character = 0;
        long lengthChar = reader.getFile().length();

        while ((character = reader.read()) != -1) {
            double probability = 0;

            if (map.containsKey(character)) {
                int lastValue = map.get(character) + 1;
                probability = lastValue / (double) lengthChar;
                map.put(character, lastValue);
                probabilityMap.put((char) character, probability);
            } else {
                map.put(character, 1);
                probability = 1 / (double) lengthChar;
                probabilityMap.put((char) character, probability);
            }
        }

        Map<Integer, Integer> sortedMap = this.sortByValue(map, false);
        Codification.setHuffmanSorted(sortedMap);

//        boolean newLine = false;
//        int lengthEncode = 0;
//        Map<Character, String> huffmanTree = new HashMap<Character, String>();
//        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
//            int key = entry.getKey();
//
//            if (huffmanTree.isEmpty()) {
//                huffmanTree.put((char) key, "1");
//            } else if (huffmanTree.size() == 1) {
//                huffmanTree.put((char) key, "0");
//            } else {
//                this.getCodification(sortedMap, probabilityMap);
//                String codification = "";
//                huffmanTree.put((char) key, codification);
//            }
//        }

        boolean newLine = false;
        int lengthEncode = 0;
        Map<Character, String> huffmanTree = new HashMap<Character, String>();
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            int key = entry.getKey();

            if (!newLine) {
                huffmanTree.put((char) key, "0");
                newLine = true;
            } else {
                huffmanTree.put((char) key, "0"+StringUtils.createStreamWithOnes(lengthEncode));
            }
            lengthEncode++;
        }

        Codification.setHuffmanTree(huffmanTree);

        for (Map.Entry<Character, String> entry : huffmanTree.entrySet()) {
            String codeword = entry.getValue();
            writer.write(codeword);
        }

        writer.close();
        reader.close();
    }

    private Map<Integer, Integer> sortByValue(Map<Integer, Integer> unsortMap, final boolean order)
    {
        List<Entry<Integer, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

    @Override
    public void decode(WriterInterface writer, ReaderInterface reader) throws IOException, WrongFormatExpection {
        Codification.setCodificationName("Huffman Estático");
        reader.readCabecalho();// apenas para passar os bits do cabeçalho
        char character;

        while ((character = (char) reader.readNextChar()) != 65535) {
            System.out.println(character);
        }

        writer.close();
        reader.close();
    }

    @Override
    public String getBitsIdentificacaoAlgoritmo(WriterInterface writer) {
        String firstByte = "00111111"; //identificaçãoAlgoritmo
        String secondByte = "00000000"; // informação extra goloumb
        return firstByte + secondByte;
    }
}
