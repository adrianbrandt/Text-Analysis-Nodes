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

        void write (){
            System.out.print(data + " x" + count + ", ");
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

        public void inOrder(textNode data){
            if (data != null){
                inOrder(data.right);
                data.write();
                inOrder(data.left);

            }
        }

        boolean contains(textNode root, String x) {
            if (root == null)
                return false;
            if (Objects.equals(root.data, x)) {
                root.count += 1;
                return true;
            }

            return (contains(root.left, x) || contains(root.right, x));
        }

    }

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


    public static void mainProgram() throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);
        File file = new File("src/input.txt");
        Scanner read = new Scanner(file);
        String freetxt;

        System.out.println("""
                 Welcome to text analysis
                 Choose starting method:
                 Keyboard input: 1
                 File input: 2
                 """);

        int ans = scan.nextInt();
        scan.nextLine();

        switch (ans){

            case 1 -> {
                System.out.println("Enter your free text");
                freetxt = scan.nextLine();
                keyInput(freetxt.toUpperCase());
            }

            case 2 -> {
                while(read.hasNextLine()){
                    freetxt = read.nextLine();
                    keyInput(freetxt.toUpperCase());
                }

            }
        }

    }

    public static void keyInput(String txt) {
        textNode tester = createTree(txt.toUpperCase());
        System.out.println("\nIN ORDER: ");
        tester.inOrder(tester);

    }


    public static void main(String[] args) throws FileNotFoundException {
        mainProgram();
    }

}