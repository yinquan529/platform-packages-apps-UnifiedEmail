/*
 * Copyright (C) 2012 Google Inc.
 * Licensed to The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.mail.browse;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.android.mail.providers.Conversation;
import com.android.mail.providers.Folder;
import com.android.mail.ui.AnimatedAdapter;
import com.android.mail.ui.AnimatedAdapter.ConversationListListener;
import com.android.mail.ui.ControllableActivity;
import com.android.mail.ui.ConversationSelectionSet;

public class SwipeableConversationItemView extends FrameLayout implements ToggleableItem {

    private final ConversationItemView mConversationItemView;

    public SwipeableConversationItemView(Context context, String account) {
        super(context);
        mConversationItemView = new ConversationItemView(context, account);
        addView(mConversationItemView);
    }

    public ListView getListView() {
        return (ListView) getParent();
    }

    public void reset() {
        mConversationItemView.reset();
    }

    public ConversationItemView getSwipeableItemView() {
        return mConversationItemView;
    }

    public void bind(Conversation conversation, ControllableActivity activity,
            final ConversationListListener conversationListListener,
            ConversationSelectionSet set, Folder folder, int checkboxOrSenderImage,
            boolean swipeEnabled, boolean priorityArrowsEnabled, AnimatedAdapter animatedAdapter) {
        mConversationItemView.bind(conversation, activity, conversationListListener, set, folder,
                checkboxOrSenderImage, swipeEnabled, priorityArrowsEnabled, animatedAdapter);
    }

    public void startUndoAnimation(AnimatorListener listener, boolean swipe) {
        final Animator a = (swipe) ? mConversationItemView.createSwipeUndoAnimation()
                : mConversationItemView.createUndoAnimation();
        a.addListener(listener);
        a.start();
    }

    public void startDeleteAnimation(AnimatorListener listener, boolean swipe) {
        final Animator a = (swipe) ? mConversationItemView.createDestroyWithSwipeAnimation()
                : mConversationItemView.createDestroyAnimation();
        a.addListener(listener);
        a.start();
    }

    @Override
    public void toggleSelectedStateOrBeginDrag() {
        if (mConversationItemView != null) {
            mConversationItemView.toggleSelectedStateOrBeginDrag();
        }
    }

    @Override
    public void toggleSelectedState() {
        if (mConversationItemView != null) {
            mConversationItemView.toggleSelectedState();
        }
    }
}
