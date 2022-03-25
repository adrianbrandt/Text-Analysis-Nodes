import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class textTree {

    private static class textNode{
        String data;
        textNode left, right;

        public int count = 1;

    public textNode(String data){
            this.data = data;
            left = right = null;
        }

        //Prints out the word and the amount of times the word was found
        void write (){
            System.out.print(data + "[" + count + "], ");
        }

        public void addNode(String data) {
            if (this.data == null) {
                this.data = data;
            } else {
                if (this.data.compareTo(data) < 0) {
                    if (this.left != null) {
                        this.left.addNode(data);
                    }
                    else {
                        this.left = new textNode(data);
                    }

                } else {
                    if (this.right != null) {
                        this.right.addNode(data);
                    } else {
                        this.right = new textNode(data);
                    }

                }
            }
        }

        //Sorts the tree in alphabetical order
        public void inOrder(textNode data){
            if (data != null){
                inOrder(data.right);
                data.write();
                inOrder(data.left);

            }
        }

        //Sorts through the tree and adds to the counter for each duplicate word|letter
        boolean contains(textNode root, String x) {
            if (root == null)
                return false;
            if (Objects.equals(root.data, x)) {
                root.count += 1;
                return true;
            }

            //Returns a boolean if the current word matches the next word
            return (contains(root.left, x) || contains(root.right, x));
        }

    }
    /*
    Creates the tree and sends the current word through the sorter to only
    put words without duplicates in the tree. Also uses a regex for symbols
    and double spaces to get rid of unwanted content that can give wrong output
     */
    public static textNode createTree(String data ) {
        textNode tree = new textNode(data);
        if( data != null ) {

            data = data.replaceAll("[^a-zA-Z0-9]", " ");
            data = data.replaceAll("  ", " ");

            String[] words = data.split( " ");

            tree = new textNode(null);
            for (String word : words) {
                if (!tree.contains(tree, word)) {
                    tree.addNode(word);
                }
            }

        }
        return tree;
    }

    //Takes input and prints it in order
    public static void keyInput(String txt) {
        textNode node = createTree(txt);
        System.out.println("\nIN ORDER: ");
        node.inOrder(node);

    }

    //Main program
    public static void main(String[] args) throws FileNotFoundException {

        //Scanner for keyboard input | Scanner for reading content of file
        Scanner scan = new Scanner(System.in);
        File file = new File("src/input.txt");
        Scanner read = new Scanner(file);
        String freetxt;

        //Start of program, user input to choose which input to use
        System.out.println("""
                 Choose starting method:
                 Keyboard input: 1
                 File input: 2
                 """);

        int ans = scan.nextInt();
        scan.nextLine();

        //Switch case
        switch (ans){

            //Case 1, takes user input and sends it to the keyInput for sorting and printing
            case 1 -> {
                System.out.println("Enter your free text");
                freetxt = scan.nextLine();
                keyInput(freetxt.toUpperCase());
            }

            //Case 2, takes content of file and sends it to the keyInput for sorting and printing
            case 2 -> {
                while(read.hasNextLine()){
                    freetxt = read.nextLine();
                    keyInput(freetxt.toUpperCase());
                }

            }
        }
    }

}