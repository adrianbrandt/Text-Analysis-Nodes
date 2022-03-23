import java.util.Scanner;

public class textTree {

    public static void mainProgram(){

        Scanner scan = new Scanner(System.in);
        String freetxt;

        System.out.println("""
                 Welcome to text analysis
                 Choose starting method:
                 Keyboard input: 1
                 File input: 2""");

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


        textTree tester = createTree(txt.toUpperCase());

        System.out.println("""
                 How would you like to see the text?
                 In order: 1
                 Pre order: 2
                 Post order: 3
                 Previous menu: 5""");

        int a1= scan.nextInt();

        switch (a1){
            case 1: {
                System.out.println("\nIN ORDER: ");
                tester.textInOrder();
                break;
            }
            case 2: {
                tester.textPreOrder();
            }
            case 3: {
                tester.textPostOrder();
            }
            case 5: {
                mainProgram();
            }
        }


    }

    private String data;
    private textTree left;
    private textTree right;

    public textTree() {
        this.data = null;
        this.left = null;
        this.right = null;
    }

    public textTree(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public static textTree createTree( String content ) {
        textTree tTree = new textTree();
        if( content != null ) {

            content = content.replaceAll("[^a-zA-Z0-9]", " ");

            String[] words = content.split( " ");

            tTree = new textTree();
            for (String word : words) {
                tTree.addNode(word);
            }

        }
        return tTree;
    }


    public void addNode(String data) {
        if (this.data == null) {
            this.data = data;
        } else {
            if (this.data.compareTo(data) < 0) {
                if (this.left != null) {
                    this.left.addNode(data);
                } else {
                    this.left = new textTree(data);
                }
            } else {
                if (this.right != null) {
                    this.right.addNode(data);
                } else {
                    this.right = new textTree(data);
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
        System.out.println(this.data);
        if (this.right != null) {
            this.right.textInOrder();
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

    public static void main(String[] args) {
        mainProgram();


    }

}