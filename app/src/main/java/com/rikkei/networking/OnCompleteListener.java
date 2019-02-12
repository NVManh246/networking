package com.rikkei.networking;

import java.util.List;

public interface OnCompleteListener {
    void onSuccess(List<Post> posts);
    void onError(Exception e);
}
