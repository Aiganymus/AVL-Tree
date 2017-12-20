class AVLTree {
    Node root;
 
    int height(Node N) { //возвращает высоту дерева
        if (N == null) return 0;
         return N.height;
    }
    
    int getBalance(Node N) { //возвращает баланс вершины N (разницу между высотой левого и правого поддерева) 
        if (N == null) return 0;
        return height(N.left) - height(N.right);
    }
    
    void print(Node node) { //делает прямой обход и выводит каждую вершину
        if (node != null) {
            System.out.print(node.value + " ");
            print(node.left);
            print(node.right);
        }
    }
    
    Node minNode(Node node) { //возвращает вершину с наименьшим значением у дерева\поддерева
        Node cur = node;
        while (cur.left != null) //спускается влево вниз, пока не дойдет до вершины с наименьшим значением
           cur = cur.left;
        return cur;
    }
 
    Node rightRotate(Node y) { //поворот вправо поддерева у 
        Node x = y.left;
        Node x2 = x.right;
        
        //поворот вправо поддерева у 
        x.right = y;
        y.left = x2; 
        
        //меняем высоту 
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x; //возвращает новую вершину
    }
 
    Node leftRotate(Node x) {
        Node y = x.right;
        Node y2 = y.left;
        
        //поворот влево поддерева х
        y.left = x;
        x.right = y2;
        
        //меняем высоту 
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y; //возвращает новую вершину
    }
 
    //добавление вершины
    Node insert(Node node, int value) {
        if (node == null) return (new Node(value));
 
        if (value < node.value)
            node.left = insert(node.left, value); //спускается влево, если добавляемая вершина меньше текущей
        else 
            node.right = insert(node.right, value); //спускается вправо, если добавляемая вершина больше текущей
 
        node.height = 1 + Math.max(height(node.left), height(node.right)); //меняем высоту предка вершины, которую добавили
 
        int balance = getBalance(node);
 
        if (balance > 1) {
        	if(getBalance(root.left) >= 0) return rightRotate(root); //если дерево стало несбалансированным, делаем поворот вправо
        	else { //поворот влево, затем вправо
        		root.left = leftRotate(root.left);
                return rightRotate(root);
        	}
        }           
        
        else if (balance < -1) {
        	if(getBalance(root.right) <= 0) return leftRotate(root); //поворот влево
        	else { //поворот вправо, затем влево
        		root.right = rightRotate(root.right);
                return leftRotate(root);
        	}
        } 
 
        return node;
    }
 
    Node delete(Node root, int value) {
        if (root == null) return root; //простое удаление вершины
        
        if (value < root.value) //спускается влево, если вершина, которую нужно удалить, меньше чем корень
            root.left = delete(root.left, value);
 
        else if (value > root.value) //спускается вправо, если вершина, которую нужно удалить, больше чем корень
            root.right = delete(root.right, value);

        else { //если вершина найдена 
            if ((root.left == null) || (root.right == null)) {
                Node tmp = null;
                if (tmp == root.left)
                    tmp = root.right;
                else
                    tmp = root.left;
 
                if (tmp == null) { //если у вершины нет потомков, то ее просто удаляют
                    tmp = root;
                    root = null;
                }
                else  //если у вершины есть потомок
                    root = tmp; 
            }
            else { //если у вершины два потомка
                Node tmp = minNode(root.right); //находим наименьшую вершину в правом поддереве
                //копирумем потомка и удаляем
                root.value = tmp.value; 
                root.right = delete(root.right, tmp.value);
            }
        }
 
        if (root == null) return root; //если у дерева была только одна вершина, то просто ее возвращаем
 
        root.height = Math.max(height(root.left), height(root.right)) + 1;
 
        int balance = getBalance(root);
 
        if (balance > 1) {
        	if(getBalance(root.left) >= 0) return rightRotate(root); //если дерево стало несбалансированным, делаем поворот вправо
        	else { //поворот влево, затем вправо
        		root.left = leftRotate(root.left);
                return rightRotate(root);
        	}
        }           
        
        else if (balance < -1) {
        	if(getBalance(root.right) <= 0) return leftRotate(root); //поворот влево
        	else { //поворот вправо, затем влево
        		root.right = rightRotate(root.right);
                return leftRotate(root);
        	}
        } 
 
        return root;
    }

}