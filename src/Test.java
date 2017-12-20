
public class Test {
	 public static void main(String[] args) {
        AVLTree tree = new AVLTree(); 
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 2);
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 4);
        tree.root = tree.insert(tree.root, 12);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 0);
        tree.root = tree.insert(tree.root, 56);
        tree.root = tree.insert(tree.root, 78); 
        tree.print(tree.root); 
        tree.root = tree.delete(tree.root, 10); 
        tree.print(tree.root);
	 }
}
