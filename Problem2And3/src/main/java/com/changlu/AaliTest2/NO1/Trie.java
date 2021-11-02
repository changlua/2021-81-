package com.changlu.AaliTest2.NO1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName Trie
 * @Author ChangLu
 * @Date 2021/10/27 20:03
 * @Description TODO
 */
class Node{
    boolean isWord;
    Map<Character,Node> next;
    public Node(boolean isWord){
        this.isWord = isWord;
        next = new TreeMap<>();
    }

    public Node(){
        this(false);
    }
}

public class Trie {

    private Node root;
    private int size;

    public Trie(){
        this.root = new Node();
        this.size = 0;
    }

    public void add(String word){
        if(word == null || "".equals(word)){
            return;
        }

        Node root = this.root;

        for (char c : word.toCharArray()) {
            if(!root.next.containsKey(c)){
                root.next.put(c,new Node());
            }
            root = root.next.get(c);
        }
        if(!root.isWord){
            root.isWord = true;
            this.size++;
        }
    }

    public List<String> matchPrefixWords(String prefix){
        List<String> words = new ArrayList<>();
        if(!contains(prefix)){
            return words;
        }
        Node cur = this.root;
        for (char c : prefix.toCharArray()) {
            cur = cur.next.get(c);
        }
        if(cur.isWord){
            words.add(prefix);
        }
        cur.next.entrySet().stream().forEach(entry->words.addAll(getChildrenWord(prefix,entry)));
//        for (Map.Entry<Character, Node> entry : cur.next.entrySet()) {
//            words.addAll(getChildrenWord(prefix,entry));
//        }
        return words;
    }

    public List<String> getChildrenWord(String prefix,Map.Entry<Character, Node> entry){
        ArrayList<String> childWords = new ArrayList<>();
        StringBuilder builder = new StringBuilder(prefix);
        builder.append(entry.getKey());
        if(entry.getValue().isWord){
            childWords.add(builder.toString());
        }
        entry.getValue().next.entrySet().stream().forEach(entry2->{
            List<String> childrenWord = getChildrenWord(builder.toString(), entry2);
            childWords.addAll(childrenWord);
        });
//        for (Map.Entry<Character, Node> entry2 : ) {
//            List<String> childrenWord = getChildrenWord(builder.toString(), entry2);
//            childWords.addAll(childrenWord);
//        }
        return childWords;
    }

    public boolean contains(String prefix){
        if(prefix == null || "".equals(prefix)){
            return false;
        }
        Node cur = this.root;
        for (char c : prefix.toCharArray()) {
            if(!cur.next.containsKey(c)){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

}