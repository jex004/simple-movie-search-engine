/*
 * Name: TODO
 * PID:  TODO
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 *
 * @author TODO
 * @since  TODO
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     *
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @return false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                // populate three trees with the information you just read
                populateTree(movieTree, cast, movie);
                populateTree(studioTree, studios, movie);
                populateTree(ratingTree, cast, rating);

                // hint: create a helper function and reuse it to build all three trees

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Populates the tree as the helper method for populateTree
     * @param tree Tree to be populated
     * @param keys Keys at which data should be inserted
     * @param data Data to be inserted
     */
    private static void populateTree(BSTree<String> tree, String[] keys, String data) {
        for (String key : keys) {
            if (!tree.findKey(key)) {
                tree.insert(key.toLowerCase());
            }
            if (!tree.findDataList(key).contains(data)) {
                tree.insertData(key, data);
            }
        }
    }

    /**
     * Search a query in a BST
     *
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {
        // process query
        String[] keys = query.toLowerCase().split(" ");
        LinkedList<String> intersectionResults = new LinkedList<>();

        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful
        if (keys.length > 1) {
            if (searchTree.findKey(keys[0])) {
                intersectionResults.addAll(searchTree.findDataList(keys[0]));
            }
            for (String key : keys) {
                if (searchTree.findKey(key)) {
                    intersectionResults.retainAll(searchTree.findDataList(key));
                }
            }
            print(query, intersectionResults);
        }


        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful
        for (String key: keys) {
            if (searchTree.findKey(key)) {
                LinkedList<String> singleResults = searchTree.findDataList(key);
                singleResults.removeAll(intersectionResults);
                intersectionResults.addAll(singleResults);
                if (!intersectionResults.isEmpty() && singleResults.isEmpty()) {
                    continue;
                }
                print(key, singleResults);
            } else {
                print(key, null);
            }
        }
    }

    /**
     * Print output of query
     *
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // initialize search trees
        BSTree<String> movieTree = new BSTree<>();
        BSTree<String> studioTree = new BSTree<>();
        BSTree<String> ratingTree = new BSTree<>();
        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);

        // populate search trees
        if (populateSearchTrees(movieTree, studioTree, ratingTree, fileName)) {
            // choose the right tree to query
            BSTree<String> searchTree;
            if (searchKind == 0) {
                searchTree = movieTree;
            } else if (searchKind == 1) {
                searchTree = studioTree;
            } else if (searchKind == 2) {
                searchTree = ratingTree;
            } else {
                return;
            }

            StringBuilder queryBuilder = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
                queryBuilder.append(args[i]).append(" ");
            }
            String query = queryBuilder.toString().trim();

            searchMyQuery(searchTree, query);
        }
    }
}
