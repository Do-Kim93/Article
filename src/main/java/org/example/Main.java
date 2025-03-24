package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==프로그램 시작==");
        int lastid = 0;
        List<Article> articles = new ArrayList<>();//db를 사용할수없어 저장소 역활을 할 리스트 생성
        while (true) {
            System.out.print("명령어)");
            String cmd = sc.nextLine().trim();
            if (cmd.equals("exit")) {
                System.out.println("사용종료");
                break;
            }
            if (cmd.equals("article write")) {
                int id = lastid + 1;
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String content = sc.nextLine().trim();
//          밑의 객체를 생성하기 위해 보낸 인자값 때문에 그걸 저장해줄 클래스단위의 전역 변수가 필요해짐
                Article article = new Article(id, title, content);//id,title,content를 매개변수로가지는 article 생성자 생성

                articles.add(article);
                System.out.println(id + "번째 글이 작성 되었습니다");
                lastid++;
            } else if (cmd.equals("article list")) {
                System.out.println("검색어를 활용 하시겠습니까? y/n");
                String answer = sc.nextLine().trim();
                if (answer.equals("y")) {
                    System.out.println("제목/내용,검색어 입력 \n ex) 제목-눈 또는 내용-꽃");
                    String answer2 = sc.nextLine().trim();
                    String[] answers;
                    try {
                        answers = answer2.split("-");
                    } catch (Exception e) {
                        System.out.println("잘못 입력하셨습니다.");
                        return;
                    }
                    String key = answers[0];
                    String value = answers[1];
                    System.out.println(value);
                    if (key.equals("제목")) {
                        for (Article article : articles) {
                            if (article.getTitle().contains(value)) {
                                System.out.println(article.getId() + "   /   " + article.getTitle() + "   /   " + article.getContent());
                            }
                        }
                        System.out.println("해당 제목의 글이 존재하지 않습니다.");
                    } else if (key.equals("내용")) {
                        for (Article article : articles) {
                            if (article.getContent().contains(value)) {
                                System.out.println(article.getId() + "   /   " + article.getTitle() + "   /   " + article.getContent());
                            }
                        }
                        System.out.println("해당 내용의 글이 존재하지 않습니다.");
                    }
                } else {
                    System.out.println("==게시글 목록==");
                    if (articles.isEmpty()) {
                        System.out.println("아무것도 없음");
                    }
                    System.out.println("번호   /   제목   /   내용    ");
                    for (int i = articles.size() - 1; i >= 0; i--) {
                        Article article = articles.get(i);//Article 모양의 변수 article에 articles의 값들을 전달
                        System.out.println(article.getId() + "   /   " + article.getTitle() + "   /   " + article.getContent());
                    }
                }

            } else if (cmd.startsWith("article detail")) {
                int id = Integer.parseInt(cmd.split(" ")[2]);
                for (Article article : articles) {
                    if (article.getId() == id) {
                        System.out.println("번호 : " + article.getId());
                        System.out.println("제목 : " + article.getTitle());
                        System.out.println("내용 : " + article.getContent());
                    } else {
                        System.out.println("없는 번호 입니다.");
                    }
                }

            } else if (cmd.startsWith("article delete")) {
                int id = Integer.parseInt(cmd.split(" ")[2]);
                for (Article article : articles) {
                    if (article.getId() == id) {
                        articles.remove(article);
                        System.out.println(id + "번 게시글이 삭제 되었습니다");
                    }
                }
            } else if (cmd.startsWith("article update")) {
                int id = Integer.parseInt(cmd.split(" ")[2]);
                System.out.println("기존제목 : " + articles.get(id - 1).getTitle());
                System.out.println("기존내용 : " + articles.get(id - 1).getContent());
                System.out.print("제목 : ");
                String newTitle = sc.nextLine().trim();
                System.out.print("내용 : ");
                String newContent = sc.nextLine().trim();
                for (Article article : articles) {
                    if (article.getId() == id) {
                        article.setTitle(newTitle);
                        article.setContent(newContent);
                    } else {
                        System.out.println("사용할수 없는 명령어 입니다.");
                    }
                }
            }
        }
        System.out.println("==프로그램 종료==");
        sc.close();
    }
}


class Article {

    private int id;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void add(Article article) {
    }
}
