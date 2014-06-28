package me.aktor.corsoapp.corso.parcelables;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * Created by Andrea Tortorella on 28/06/14.
 */
public class Post implements Parcelable{

    public final String author;
    public final String content;

    public Post(String author, String content) {
        this.author = author;
        this.content = content;
    }

    private Post(Parcel source) {
        this.author =source.readInt()==1? source.readString():null;
        this.content =source.readInt()==1? source.readString():null;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (author!=null){
            dest.writeInt(1);
            dest.writeString(author);
        } else {
            dest.writeInt(0);
        }
        if (content!=null){
            dest.writeInt(1);
            dest.writeString(content);
        } else {
            dest.writeInt(0);
        }
    }

    public final String getAuthor() {
        return author;
    }

    public final String getContent() {
        return content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };



    static {
        Bundle b = new Bundle();
        b.putParcelable("chia",new Post("ciao","ciao"));
        Post p =b.getParcelable("chia");
    }


    static {
        // dal database
        Cursor c = getCursor();
        Post p = Post.from(c);
//        PostUtils.bind(view,c);

        //Post.findAll();
//        bindView(p);
        //p.toJson();
    }

    private static Post from(Cursor c) {
        return null;
    }

    private static Cursor getCursor() {
        return null;
    }


}
