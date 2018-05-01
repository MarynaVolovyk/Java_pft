package ru.stqa.pft.adressbook;

import org.testng.annotations.Test;

public class GroupDeletion extends TestBase {



    @Test
    public void testGroupDeletion() {

        gotoGroupPage();
        selectGroup();
        deleteSelectedGroup();
        returnToGroupPage();
    }

}
