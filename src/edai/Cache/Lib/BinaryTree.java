package edai.Cache.Lib;

public class BinaryTree<T extends Comparable<T>> {

    private TreeNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    //region Size
    public int size() {
        if (root == null) return 0;

        return countNodes(root);
    }

    private int countNodes(TreeNode<T> visitedNode) {
        if (visitedNode == null) return 0;

        return countNodes(visitedNode.getLeft())
                + countNodes(visitedNode.getRight())
                + 1;
    }

    //endregion

    //region Insert
    public void insert(T data) {
        TreeNode<T> newNode = new TreeNode<T>(data);
        if (root == null) {
            root = newNode;
        } else {
            insertNode(root, newNode);
        }
    }

    private void insertNode(TreeNode<T> visitedNode, TreeNode<T> newNode) {
        assert visitedNode != null;

        int compareResult = newNode.getData().compareTo(visitedNode.getData());

        if(compareResult == 0){
            visitedNode.setData( newNode.getData() );
            return;
        }

        if (compareResult < 0) {
            if (visitedNode.getLeft() == null) {
                visitedNode.setLeft(newNode);
            } else {
                insertNode(visitedNode.getLeft(), newNode);
            }
        } else {
            if (visitedNode.getRight() == null) {
                visitedNode.setRight(newNode);
            } else {
                insertNode(visitedNode.getRight(), newNode);
            }
        }
    }
    //endregion

    //region Search
    public TreeNode<T> search(T data){
        TreeNode<T> currentNode = root;
        while(currentNode != null){
            int compareResult = data.compareTo(currentNode.getData());
            if(compareResult == 0){
                return currentNode;
            }else if(compareResult < 0){
                currentNode = currentNode.getLeft();
            } else if (compareResult >0){
                currentNode = currentNode.getRight();
            }else{
                return currentNode;
            }
        }

        return null;
    }
    //endregion

    //region Remove
    public void remove(T value) {
        TreeNode<T> nodeToRemove = search(value);
        if (nodeToRemove == null) return;

        TreeNode<T> parent = findParent(nodeToRemove);
        final boolean hasLeft = nodeToRemove.getLeft() != null;
        final boolean hasRight = nodeToRemove.getRight() != null;

        if (hasRight && hasLeft) {
            removeNodeWithTwoChildren(parent, nodeToRemove);
        } else {
            removeNodeWithZeroOrOneChild(parent, nodeToRemove);
        }
    }
    private void removeNodeWithZeroOrOneChild(TreeNode<T> parent, TreeNode<T> nodeToRemove) {
        TreeNode<T> replacement = nodeToRemove.getLeft() != null ?
                nodeToRemove.getLeft() :
                nodeToRemove.getRight();
        if (parent != null) {
            if (parent.getLeft() == nodeToRemove) {
                parent.setLeft(replacement);
            } else if (parent.getRight() == nodeToRemove) {
                parent.setRight(replacement);
            }
        } else {
            this.root = replacement;
        }
    }

    private void removeNodeWithTwoChildren(TreeNode<T> parent, TreeNode<T> nodeToRemove) {
        TreeNode<T> replacement = findMostLeftNode(nodeToRemove.getRight());

        removeNodeWithZeroOrOneChild(findParent(replacement), replacement);

        replacement.setLeft(nodeToRemove.getLeft());
        replacement.setRight(nodeToRemove.getRight());

        if (parent != null) {
            if (parent.getLeft() == nodeToRemove) {
                parent.setLeft(replacement);
            } else if (parent.getRight() == nodeToRemove) {
                parent.setRight(replacement);
            }
        } else {
            root = replacement;
        }

    }

    private TreeNode<T> findMostLeftNode(TreeNode<T> visitedNode) {
        if (visitedNode.getLeft() == null) return visitedNode;

        return findMostLeftNode(visitedNode.getLeft());
    }

    private TreeNode<T> findParent(TreeNode<T> node) {
        return findParent(root, node);
    }

    private TreeNode<T> findParent(TreeNode<T> visitedNode, TreeNode<T> node) {
        if (visitedNode == null) return null;

        if (visitedNode.getRight() == node || visitedNode.getLeft() == node) {
            return visitedNode;
        }

        final int comparisonResult = node.getData().compareTo(visitedNode.getData());
        final boolean valueIsSmaller = comparisonResult < 0;

        if (valueIsSmaller) {
            return findParent(visitedNode.getLeft(), node);
        } else {
            return findParent(visitedNode.getRight(), node);
        }
    }
    //endregion

    //region Array
    public Object[] listData() {
        Object[] output = new Object[size()];
        fillArrayInOrder(root, output, 0);
        return output;
    }

    private int fillArrayInOrder(TreeNode<T> visitedNode, Object[] output, int firstIndex) {
        if (visitedNode == null) return 0;

        final int leftCount = fillArrayInOrder(visitedNode.getLeft(), output, firstIndex);

        final int currentIndex = firstIndex + leftCount;
        output[currentIndex] = visitedNode.getData();

        final int firstRightIndex = currentIndex + 1;
        final int rightCount = fillArrayInOrder(visitedNode.getRight(), output, firstRightIndex);

        return 1 + leftCount + rightCount;
    }
    //endregion
}




