package entity;

/**
 * Created by nicole on 2018-02-26.
 */

    public class Post_ListView {
        private String title;
        private String body;
        private int imageId;
        private String time;


        public Post_ListView(String title, String body, int imageId, String time){
            this.title = title;
            this.imageId = imageId;
            this.body = body;
            this.time = time;
        }
        public String getTitle(){
            return title;
        }
        public String getBody(){
            return body;
        }
        public int getImageId(){
            return imageId;
        }
        public String getTime(){
            return time;
        }
}
