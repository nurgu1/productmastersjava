package seven;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post implements Serializable {
    private static final long serialVersionUID = 1L; // Для контроля версии сериализации
    private static int nextId = 1; // Статическая переменная для генерации уникальных ID
    private final int id; // Уникальный ID поста
    private final User author; // Автор поста
    private final String content; // Текст поста
    private int likes; // Количество лайков
    private int reposts; // Количество репостов
    private final Date createdAt; // Дата создания поста
    private final List<String> comments; // Список комментариев

    public Post(User author, String content) {
        this.id = nextId++;
        this.author = author;
        this.content = content.length() > 280 ? content.substring(0, 280) : content; // Ограничение длины текста
        this.likes = 0;
        this.reposts = 0;
        this.createdAt = new Date();
        this.comments = new ArrayList<>();
    }

    // Добавить комментарий
    public void addComment(String comment) {
        comments.add(comment);
    }

    // Получить список комментариев
    public List<String> getComments() {
        return comments;
    }

    // Получить ID поста
    public int getId() {
        return id;
    }

    // Получить автора поста
    public User getAuthor() {
        return author;
    }

    // Получить текст поста
    public String getContent() {
        return content;
    }

    // Получить количество лайков
    public int getLikes() {
        return likes;
    }

    // Получить количество репостов
    public int getReposts() {
        return reposts;
    }

    // Получить дату создания поста
    public Date getCreatedAt() {
        return createdAt;
    }

    // Лайкнуть пост
    public void like() {
        likes++;
    }

    // Сделать репост
    public void repost() {
        reposts++;
    }

    @Override
    public String toString() {
        return String.format("Post{id=%d, author=%s, content='%s', likes=%d, reposts=%d, comments=%s}",
                id, author, content, likes, reposts, comments);
    }
}