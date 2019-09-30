package org.woehlke.jakartaee.petclinic.frontend.web.common;

import org.primefaces.model.TreeNode;

import java.io.Serializable;

public interface HasTreeNode  extends Serializable {

    TreeNode getTreeNodeRoot();
    void setTreeNodeRoot(TreeNode treeNodeRoot);
    void initTreeNodes();
}
