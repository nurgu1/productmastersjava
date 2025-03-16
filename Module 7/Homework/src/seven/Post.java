package seven;

import java.util.Date;

public class Post {
    private final int id;
    private final User author;
    private final String content;
    private int likes;
    private int reposts;
    private final Date createdAt;
    private static int nextId = 1;

    public Post(User author, String content) {
        this.id = nextId++;
        this.author = author;
        this.content = content.length() > 280 ? content.substring(0, 280) : content;
        this.likes = 0;
        this.reposts = 0;
        this.createdAt = new Date();
    }

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public int getReposts() {
        return reposts;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void like() {
        likes++;
    }

    public void repost() {
        reposts++;
    }

    @Override
    public String toString() {
        return String.format("Post{id=%d, author=%s, content='%s', likes=%d, reposts=%d}", id, author, content, likes, reposts);
    }
}