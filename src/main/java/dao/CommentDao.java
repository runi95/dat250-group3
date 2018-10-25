package dao;

import entities.Comment;

import javax.ejb.Stateless;

@Stateless
public class CommentDao extends AbstractDao<Comment> {

    public CommentDao() {
        super(Comment.class);
    }
}
