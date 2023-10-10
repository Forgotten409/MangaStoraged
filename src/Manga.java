public class Manga {
    public String name;
    public String chapter;
    public  String imagefilepath;

    public Manga(String name, String chapter, String imagefilepath) {
        this.name = name;
        this.chapter = chapter;
        this.imagefilepath = imagefilepath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getImagefilepath() {
        return imagefilepath;
    }

    public void setImagefilepath(String imagefilepath) {
        this.imagefilepath = imagefilepath;
    }
}
