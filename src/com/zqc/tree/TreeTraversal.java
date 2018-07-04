package com.zqc.tree;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Interllij IDEA
 * User:Zqc
 * Data:2018/7/4
 * Time:10:19
 * Email:quanchaozhao@yeah.net
 * To change this template use File | Settings | File Templates.
 */
public class TreeTraversal {
    public static void main(String[] args) {
        List<Integer> preLst = new ArrayList<>();
        List<Integer> inList = new ArrayList<>();
        int[] nums1 = {3,4,1,2,7,8};
        int[] nums2 = {1,4,2,3,7,8};
        System.out.println("二叉树，前序序列为：");
        for (int x:nums1
             ) {
            System.out.print(x + " ");
            preLst.add(x);
        }
        System.out.println();
        System.out.println("二叉树，中序序列为：");
        for (int x:nums2
             ) {
            System.out.print(x + " ");
            inList.add(x);
        }
        System.out.println();
        TreeTraversal treeTraversal = new TreeTraversal();
        TreeNode root = treeTraversal.createTree(preLst,inList,6);
        List<Integer> totalList =treeTraversal.inorderTraversal(root);
        System.out.println("根据二叉树的前序和中序序列，构建二叉树。\n尝试中序遍历如下：");
        for (int values:totalList
             ) {
            System.out.print(values + " ");
        }
        System.out.println("over");
        List<Integer> totalList2 =treeTraversal.preorderTraversal(root);
        System.out.println("尝试前序序遍历如下：");
        for (int values:totalList2
                ) {
            System.out.print(values + " ");
        }
        System.out.println("over");
        List<Integer> totalList3 =treeTraversal.postorderTraversal(root);
        System.out.println("尝试前序序遍历如下：");
        for (int values:totalList3
                ) {
            System.out.print(values + " ");
        }
        System.out.println("over");


    }
    public TreeNode createTree(List<Integer> preList, List<Integer> inList, int MAX){
        if(MAX == 0){
            return null;
        }
        int value = preList.get(0);
        TreeNode root = new TreeNode(value);
        List<Integer> leftPre = new ArrayList<>();
        List<Integer> rightPre = new ArrayList<>();
        List<Integer> leftIn = new ArrayList<>();
        List<Integer> rightIn = new ArrayList<>();

        int leftCountI,rightCountI;
         leftCountI = rightCountI = 0;
        int i;
        boolean flag = true;
        for(i = 0; i < MAX; i++){
            if(value == inList.get(i)){
                flag = false;
                continue;
            }
            if(flag){
                leftIn.add(inList.get(i));
                leftCountI++;
            } else {
                rightIn.add(inList.get(i));
                rightCountI++;
            }
        }

        for(i = 1; i < MAX; i++){
            if(i <= leftCountI){
                leftPre.add(preList.get(i));
            } else {
                rightPre.add(preList.get(i));
            }
        }
        root.left = createTree(leftPre,leftIn,leftCountI);
        root.right = createTree(rightPre,rightIn,rightCountI);

        return root;
    }
    public void preOrder(TreeNode node,List total){
        if(node == null){
            return;
        }

        total.add(node.val);
        inOrder(node.left,total);
        inOrder(node.right,total);
    }
    public void inOrder(TreeNode node, List total){
        if(node == null){
            return;
        }
        inOrder(node.left,total);
        total.add(node.val);
        inOrder(node.right,total);
    }
    public void postOrder(TreeNode node, List total){
        if(node == null){
            return;
        }
        postOrder(node.left,total);
        postOrder(node.right,total);
        total.add(node.val);
    }
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> totalList = new ArrayList<>();
        inOrder(root, totalList);
        return totalList;
    }
    public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> totalList = new ArrayList<>();
        preOrder(root, totalList);
        return totalList;
    }
    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> totalList = new ArrayList<>();
        postOrder(root, totalList);
        return totalList;
    }
}
