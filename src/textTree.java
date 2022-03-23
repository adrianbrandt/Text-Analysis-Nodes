import java.util.Objects;
import java.util.Scanner;

public class textTree {

    private static class textNode{
        String data;
        textNode left, right;
        private textNode root;

    public textNode(String data){
            this.data = data;
            left = right = null;
        }

        void write (){
            System.out.print(data + " ");
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

        public void textPreOrder() {
            System.out.println(this.data);
            if (this.left != null) {
                this.left.textPreOrder();
            }
            if (this.right != null) {
                this.right.textPreOrder();
            }
        }

        public void textInOrder() {
            if (this.left != null) {
                this.left.textInOrder();
            }
            System.out.println(data + " ");
            if (this.right != null) {
                this.right.textInOrder();
            }
        }

        public void inOrder(textNode data){
            if (data != null){
                inOrder(data.right);
                data.write();
                inOrder(data.left);

            }
        }


        public void textPostOrder() {
            if (this.left != null) {
                this.left.textPostOrder();
            }
            if (this.right != null) {
                this.right.textPostOrder();
            }
            System.out.println(this.data);
        }

        boolean contains(textNode root, String x) {
            if (root == null)
                return false;
            if (Objects.equals(root.data, x))
                return true;

            return (contains(root.left, x) || contains(root.right, x));
        }


    }


    public static textNode createTree(String data ) {
        textNode tree = new textNode(data);
        if( data != null ) {

            data = data.replaceAll("[^a-zA-Z0-9]", " ");

            String[] words = data.split( " ");

            tree = new textNode(null);
            for (String word : words) {
                if (!tree.contains(tree,word))
                tree.addNode(word);
            }

        }
        return tree;
    }

    public static void mainProgram(){

        Scanner scan = new Scanner(System.in);
        String freetxt;

        System.out.println("""
                 Welcome to text analysis
                 Choose starting method:
                 Keyboard input: 1
                 File input: 2
                 
                 WORDS ARE SORTED FROM BOTTOM(LEFT) TO TOP(RIGHT)""");

        int ans = scan.nextInt();
        scan.nextLine();

        switch (ans){

            case 1 -> {
                System.out.println("Enter your free text");
                freetxt = scan.nextLine();

                keyInput(freetxt.toUpperCase());

            }

            case 2 -> {
                System.out.println("2");
            }
        }

    }

    public static void keyInput(String txt) {
        Scanner scan = new Scanner(System.in);

        textNode tester = createTree(txt.toUpperCase());

        System.out.println("""
                 How would you like to see the text?
                 In order: 1
                 Pre order: 2
                 Post order: 3
                 Previous menu: 5""");

        int a1= scan.nextInt();

        switch (a1){
            case 1 -> {
                System.out.println("\nIN ORDER: ");
                tester.inOrder(tester);

            }
            case 2 -> {
                System.out.println("PRE ORDER");
                tester.textPreOrder();
            }
            case 3 -> {
                System.out.println("POST ORDER");
                tester.textPostOrder();
            }
            case 5 -> {
                mainProgram();
            }
        }


    }









    public static void main(String[] args) {
        mainProgram();


    }

}