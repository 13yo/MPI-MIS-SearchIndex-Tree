package de.mpg.mis.neuesbibliothekssystem.misTree.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Root;

/**
 * This class provides combinations of different objects.
 * 
 * If you have a set of objects and select some of them, that selection without
 * regard to their order is called a combination. If you have n objects there
 * are 2n (2 to the nth power) possible combinations ranging from none present
 * to all present. Write a program or an algorithm that returns all the
 * combinations that can be made out of a given string. For example if the input
 * is "abc" it must return:
 * 
 * a b c aa ab ac ba bb bc ca cb cc aaa aab aac aba abb abc aca acb acc ... ...
 * ... etc.
 * 
 * @author Brighton Kukasira
 */
@Service
public class Permutation {

    @Autowired
    private TreeBuilder treeBuilder;

    public void perm1(String s, Root tree) {
	perm1("", s, tree);
    }

    private void perm1(String prefix, String s, Root tree) {
	int N = s.length();
	if (N == 0) {
	    // System.out.println(prefix);
	    treeBuilder.addWordToTree(prefix, tree);
	} else {
	    for (int i = 0; i < N; i++) {
		perm1(prefix + s.charAt(i),
			s.substring(0, i) + s.substring(i + 1, N), tree);
	    }
	}
    }

    // public static void main(String[] args) {
    // String alphabet = "Test";
    // String elements = alphabet.substring(0, alphabet.length());
    // perm1(elements);
    // }
}
