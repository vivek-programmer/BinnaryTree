package BinnaryTree;

import java.util.*;

public class Tree {
    static class node {
        int data;
        node left;
        node right;
        node(int data){
            this.data=data;
            this.left=null;
           this.right=null;
        }
    }
    static class BinaryTree{
        static int idx=-1;
        public static node Buildtree(int nodes[]){
            idx++;
            if(nodes[idx]==-1){
                return null ;
            }
            node newnode=new node(nodes[idx]);
            newnode.left=Buildtree(nodes);
            newnode.right=Buildtree(nodes);
            return newnode;
        }
        public static void preorder(node root){
            if(root==null){
                return ;
            }
            System.out.print(root.data+" ");
            preorder(root.left);
            preorder(root.right);
        }
        public static void inorder(node root){
            if(root==null){
                return;
            }
            inorder(root.left);
            System.out.print(root.data +" ");
            inorder(root.right);
        }
        public static void postorder(node root){
            if(root==null){
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data +" ");
           
        }
        public static void Levelorder(node root){
            if(root==null){
                return;
            }
            Queue<node>q=new LinkedList<>();
            q.add(root);
            q.add(null);
            while(!q.isEmpty()){
                node currnode=q.remove();
                if(currnode==null){
                    if(q.isEmpty()){
                        break;
                    }
                    else {
                        System.out.println();
                        q.add(null);
                    }
                }
                else {
                    System.out.print(currnode.data+" ");
                    if(currnode.left!=null){
                        q.add(currnode.left);
                    }
                    if(currnode.right!=null){
                        q.add(currnode.right);
                    }
                }
            }
        }
    }
    public static int height(node root){
        if(root==null){
            return 0;
        }
        int lh=height(root.left);
        int rh=height(root.right);
        return (Math.max(lh, rh)+1);

    }
    public static int count (node root){
        if(root==null){
            return 0;
        }
        int lc=count(root.left);
        int rc=count(root.right);
        return (lc+rc+1);
    }
    public static int diameter(node root){
        if(root==null){
            return 0;
        }
        int leftdia=diameter(root.left);
        int rightdia=diameter(root.right);
        int lh=height(root.left);
        int rh=height(root.right);
        int selfdia=lh+rh+1;
        return Math.max(leftdia, Math.max(rightdia, selfdia));
    }
    static class Info{
        int dia;
        int ht;
        public Info(int dia,int ht){
            this.dia=dia;
            this.ht=ht;
        }
    }
    public static Info diameter2(node root){
        if(root==null){
           return new Info(0,0);
        }
        Info leftinfo=diameter2(root.left);
        Info rightInfo=diameter2(root.right);

        int dia=Math.max(leftinfo.dia,Math.max(rightInfo.dia, leftinfo.ht+rightInfo.ht+1));
        int ht=Math.max(leftinfo.ht, rightInfo.ht)+1;
        return new Info(dia,ht);

    }
    public static boolean isIndetical(node node,node subrrot){
        if(node==null&&subrrot==null){
            return true;
        }
        else if(node==null||subrrot==null||node.data!=subrrot.data){
            return false;
        }
        if(!isIndetical(node.left, subrrot.left)){
            return false;
        } 
        if(!isIndetical(node.right, subrrot.right)){
            return false; 
        }
        return true;
    }
    
    public static boolean isSubtree(node root,node subrrot){
        if(root==null){
            return false;
        }
        if(root.data==subrrot.data){
            if(isIndetical(root,subrrot)){
                return true; 
            }
        }
        boolean leftans=isSubtree(root.left, subrrot);
        boolean rightans=isSubtree(root.right, subrrot);
        return leftans||rightans;
    }
    static class Infos{
        node node;
        int hd;
        public Infos(node node,int hd){
            this.node=node;
            this.hd=hd;
        }
    }
    public static void topview(node root){
        Queue<Infos>q=new LinkedList<>();
        HashMap<Integer,node>map= new HashMap<>();
        int min=0,max=0;
        q.add(new Infos(root, 0));
        q.add(null); 
        while(!q.isEmpty()){
            Infos curr=q.remove();
            if(curr==null){
                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }
            }
            else{
                if(!map.containsKey(curr.hd)){
                    map.put(curr.hd, curr.node);
                }
                if(curr.node.left!=null){
                    q.add(new Infos(curr.node.left, curr.hd-1));
                    min=Math.min(curr.hd-1, min);
                }
                if(curr.node.right!=null){
                    q.add(new Infos(curr.node.right, curr.hd+1));
                    max= Math.max(curr.hd+1,max);
                }
            }
        }
        for(int i=min;i<=max;i++){
            System.out.print(map.get(i).data+" ");
        }
    }

    public static void kThlvevel(node root,int level,int k){
        if(root==null){
            return;
        }
        if(level==k){
            System.out.print(root.data+" ");
        }
        kThlvevel(root.left, level+1, k);
        kThlvevel(root.right, level+1, k);
    }
    public static boolean getpath(node root,int n,ArrayList<node>path){
        if(root==null){
            return false;
        }
          path.add(root);
          if(root.data==n){
            return true;
          }
          boolean founleft=getpath(root.left, n, path);
          boolean founright=getpath(root.right, n, path);
          if(founleft||founright){
            return true;
          }
          path.remove(path.size()-1);
          return false;
    }
    public static node lca(node root,int n1,int n2){
        ArrayList<node>path1=new ArrayList<>();
        ArrayList<node>path2=new ArrayList<>();
        getpath(root,n1,path1);
        getpath(root,n2,path2);
        int i=0;
        for(;i<path1.size()&&i<path2.size();i++){
            if(path1.get(i)!=path2.get(i)){
                break;
            }
        }
        return path1.get(i-1);
    }
    public static node lca2(node root,int n1,int n2){
        if(root==null||root.data==n1||root.data==n2){
            return root;
        }
        node leftlca=lca2(root.left, n1, n2);
        node rightlca=lca2(root.right, n1, n2);
        if(rightlca==null){
            return leftlca;
        }
        if(leftlca==null){
            return rightlca;
        }
        return root;
    } 
    public static int getdis(node lca,int n){
        if(lca==null){
            return -1;
        }
        if(lca.data==n){
            return 0;
        }
        int leftdis=getdis(lca.left, n);
        int rightdis=getdis(lca.right, n);

        if(rightdis==-1&&leftdis==-1){
            return -1;
        }
        else if (rightdis==-1) {
            return leftdis+1;
        }
        else { 
            return rightdis+1; 
        }
    }
    public static int mindis(node root,int n1,int n2){
        node lca=lca2(root, n1, n2);
        int d1=getdis(lca,n1);
        int d2=getdis(lca,n2);

        return d1+d2;
    }
    public static int kthancetor(node root,int n,int k){
        if(root==null){
            return -1;
        }
        if(root.data==n){
            return 0;
        }
        int leftdis=kthancetor(root.left, n, k);
        int rightdis=kthancetor(root.right, n, k);
        int max=Math.max(leftdis, rightdis);
        if(max==k){
            System.out.println(root.data);
        }
        return max+1;
    }
    public static int  tranform(node root){
        if(root==null){
            return 0;
        }
        int data=root.data;
        int leftchild=tranform(root.left);
        int rightchild=tranform(root.right);
        int newleft=root.left==null?0:root.left.data;
        int newright=root.right==null?0:root.right.data;
         root.data=leftchild+rightchild+newleft+newright;
         return data;
    }
    public static void Preorder(node root){
        if(root==null){
            return ;
        }
        System.out.println(root.data+" "); 
        Preorder(root.left);
        Preorder(root.right);
    }
    public static void main(String[] args) {
        int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        int subnotes[]={5,-1,-1,3,-1,6,-1,-1};
        BinaryTree tree= new BinaryTree();
          node root= tree.Buildtree(nodes);
          tranform(root);
          Preorder(root);
    }
}
