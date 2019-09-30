package org.woehlke.jakartaee.petclinic.frontend.web.common;

import org.primefaces.model.TreeNode;

public interface HasTreeNode {

    TreeNode getTreeNodeRoot();
    void setTreeNodeRoot(TreeNode treeNodeRoot);
    void initTreeNodes();
}
