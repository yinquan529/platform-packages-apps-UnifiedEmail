/*******************************************************************************
 *      Copyright (C) 2012 Google Inc.
 *      Licensed to The Android Open Source Project.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 *******************************************************************************/

package com.android.mail.ui;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.os.Bundle;
import android.view.Menu;

import com.android.mail.providers.Account;
import com.android.mail.providers.Folder;

/**
 * ActionBarView simplifies supporting both MailActionBar and MailActionBarDeprecated (used for
 * pre-v14 devices).
 */

public interface ActionBarView extends OnNavigationListener, ViewMode.ModeChangeListener {
    /**
     * Initialize the ActionBarView
     * @param activity
     * @param callback
     * @param viewMode
     * @param actionBar
     */
    void initialize(RestrictedActivity activity, ActivityController controller, ViewMode viewMode,
            ActionBar actionBar);

    /**
     * Handle handleRestore from the Android framework.
     * @param savedInstanceState
     */
    void handleRestore(Bundle savedInstanceState);

    /**
     * Handle onResume from the Android framework.
     */
    void onResume();

    /**
     * Handle onPause from the Android framework.
     */
    void onPause();

    /**
     * @param outState
     */
    void handleSaveInstanceState(Bundle outState);

    /**
     * @param mAccountNames
     * @param currentAccount
     */
    void updateActionBar(String[] mAccountNames, String currentAccount);

    /**
     * Update the folder that the user is currently viewing
     * @param folder
     */
    void setFolder(Folder folder);

    /**
     * Returns the menu ID for the menu in this actionbar.
     * @return the Menu ID for the menu.
     */
    int getOptionsMenuId();

    /**
     * Shows a back button in the top left. We have progressed one level into the application.
     */
    void setBackButton();

    /**
     * Removes any back button from the top left. We have returned to the top of the application.
     */
    void removeBackButton();

    /**
     * Prepares all the icons that go inside the options menu. This depends on the context of
     * the action bar.
     * @param menu
     */
    boolean prepareOptionsMenu(Menu menu);

    /**
     * Creates the first time options menu.
     * @param menu
     */
    boolean createOptionsMenu(Menu menu);

    /**
     * Updates the action bar based on a new status received from the server.
     * @param account
     * @param status
     */
    void onStatusResult(String account, int status);

    /**
     * Sets the list of accounts in the drop-down navigation.
     * @param accounts the array of accounts to be set.
     */
    void setAccounts(Account[] accounts);
}
