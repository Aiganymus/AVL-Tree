class AVLTree {
    Node root;
 
    int height(Node N) { //���������� ������ ������
        if (N == null) return 0;
         return N.height;
    }
    
    int getBalance(Node N) { //���������� ������ ������� N (������� ����� ������� ������ � ������� ���������) 
        if (N == null) return 0;
        return height(N.left) - height(N.right);
    }
    
    void print(Node node) { //������ ������ ����� � ������� ������ �������
        if (node != null) {
            System.out.print(node.value + " ");
            print(node.left);
            print(node.right);
        }
    }
    
    Node minNode(Node node) { //���������� ������� � ���������� ��������� � ������\���������
        Node cur = node;
        while (cur.left != null) //���������� ����� ����, ���� �� ������ �� ������� � ���������� ���������
           cur = cur.left;
        return cur;
    }
 
    Node rightRotate(Node y) { //������� ������ ��������� � 
        Node x = y.left;
        Node x2 = x.right;
        
        //������� ������ ��������� � 
        x.right = y;
        y.left = x2; 
        
        //������ ������ 
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x; //���������� ����� �������
    }
 
    Node leftRotate(Node x) {
        Node y = x.right;
        Node y2 = y.left;
        
        //������� ����� ��������� �
        y.left = x;
        x.right = y2;
        
        //������ ������ 
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y; //���������� ����� �������
    }
 
    //���������� �������
    Node insert(Node node, int value) {
        if (node == null) return (new Node(value));
 
        if (value < node.value)
            node.left = insert(node.left, value); //���������� �����, ���� ����������� ������� ������ �������
        else 
            node.right = insert(node.right, value); //���������� ������, ���� ����������� ������� ������ �������
 
        node.height = 1 + Math.max(height(node.left), height(node.right)); //������ ������ ������ �������, ������� ��������
 
        int balance = getBalance(node);
 
        if (balance > 1) {
        	if(getBalance(root.left) >= 0) return rightRotate(root); //���� ������ ����� ������������������, ������ ������� ������
        	else { //������� �����, ����� ������
        		root.left = leftRotate(root.left);
                return rightRotate(root);
        	}
        }           
        
        else if (balance < -1) {
        	if(getBalance(root.right) <= 0) return leftRotate(root); //������� �����
        	else { //������� ������, ����� �����
        		root.right = rightRotate(root.right);
                return leftRotate(root);
        	}
        } 
 
        return node;
    }
 
    Node delete(Node root, int value) {
        if (root == null) return root; //������� �������� �������
        
        if (value < root.value) //���������� �����, ���� �������, ������� ����� �������, ������ ��� ������
            root.left = delete(root.left, value);
 
        else if (value > root.value) //���������� ������, ���� �������, ������� ����� �������, ������ ��� ������
            root.right = delete(root.right, value);

        else { //���� ������� ������� 
            if ((root.left == null) || (root.right == null)) {
                Node tmp = null;
                if (tmp == root.left)
                    tmp = root.right;
                else
                    tmp = root.left;
 
                if (tmp == null) { //���� � ������� ��� ��������, �� �� ������ �������
                    tmp = root;
                    root = null;
                }
                else  //���� � ������� ���� �������
                    root = tmp; 
            }
            else { //���� � ������� ��� �������
                Node tmp = minNode(root.right); //������� ���������� ������� � ������ ���������
                //��������� ������� � �������
                root.value = tmp.value; 
                root.right = delete(root.right, tmp.value);
            }
        }
 
        if (root == null) return root; //���� � ������ ���� ������ ���� �������, �� ������ �� ����������
 
        root.height = Math.max(height(root.left), height(root.right)) + 1;
 
        int balance = getBalance(root);
 
        if (balance > 1) {
        	if(getBalance(root.left) >= 0) return rightRotate(root); //���� ������ ����� ������������������, ������ ������� ������
        	else { //������� �����, ����� ������
        		root.left = leftRotate(root.left);
                return rightRotate(root);
        	}
        }           
        
        else if (balance < -1) {
        	if(getBalance(root.right) <= 0) return leftRotate(root); //������� �����
        	else { //������� ������, ����� �����
        		root.right = rightRotate(root.right);
                return leftRotate(root);
        	}
        } 
 
        return root;
    }

}