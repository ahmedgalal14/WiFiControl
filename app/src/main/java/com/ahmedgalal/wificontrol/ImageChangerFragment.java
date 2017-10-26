package com.ahmedgalal.wificontrol;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by AhmedGalal on 10/26/2017.
 */

public class ImageChangerFragment extends Fragment {
    Button imageChangeButton;
    ImageView currentImage;
    View view;
    String toSend = "change";
    String Message = "change";
    private ChatManager chatManager;
    String backgroundImageName;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_image, container, false);
        imageChangeButton = (Button) view.findViewById(R.id.chang_image_view_button);

        currentImage = (ImageView) view.findViewById(R.id.to_change_image_view);
        imageChangeButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        backgroundImageName = String.valueOf(currentImage.getTag());
                        if (chatManager != null) {

                            chatManager.write(toSend.getBytes());
                            if (Message != null && Message.equals(toSend)) {
                                Log.i("message check", Message);

                                if (backgroundImageName.equals("before")) {
                                    currentImage.setImageResource(R.drawable.robot);
                                    currentImage.setTag("after");
                                    Log.i("image", "here robot");
                                } else {
                                    currentImage.setImageResource(R.mipmap.ic_launcher);
                                    currentImage.setTag("before");
                                    Log.i("image", "here image");
                                }
                            } else {
                                Log.i("message check", "not here");
                            }

                        }
                    }
                });
        return view;
    }
    public interface ImageChanger {
        Handler getHandler();
    }

    public void pushMessage(String readMessage) {
        backgroundImageName = String.valueOf(currentImage.getTag());
        Message = readMessage;
        Log.i("message red", Message);
        if (Message != null && Message.equals(toSend)) {
            Log.i("message check", Message);

            if (backgroundImageName.equals("before")) {
                currentImage.setImageResource(R.drawable.robot);
                currentImage.setTag("after");
                Log.i("image", "here robot");
            } else {
                currentImage.setImageResource(R.mipmap.ic_launcher);
                currentImage.setTag("before");
                Log.i("image", "here image");
            }
        }
    }

    public void setChatManager(ChatManager obj) {
        chatManager = obj;
    }
}
