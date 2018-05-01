package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletion extends TestBase {



    @Test
    public void testGroupDeletion() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
    }

}
