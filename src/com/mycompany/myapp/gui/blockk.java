package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;


public class blockk extends Form  {
    public blockk(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Label gui_Label = new com.codename1.ui.Label();
    protected com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableX(true);
        setScrollableY(true);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("blockk");
        setName("blockk");
        gui_Label.setPreferredSizeStr("20.110077mm 22.015242mm");
                gui_Label.setInlineStylesTheme(resourceObjectInstance);
        gui_Label.setInlineAllStyles("transparency:46; opacity:255; bgImage:blockkk.png;");
        gui_Label.setName("Label");
        gui_Label_1.setPreferredSizeStr("61.600338mm 19.263336mm");
        gui_Label_1.setText("SORRY , You are BLOCKED");
                gui_Label_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_1.setName("Label_1");
        addComponent(gui_Label);
        addComponent(gui_Label_1);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label.getParent().getLayout()).setInsets(gui_Label, "17.797886% auto auto 42.01571%").setReferenceComponents(gui_Label, "-1 -1 -1 -1").setReferencePositions(gui_Label, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label_1.getParent().getLayout()).setInsets(gui_Label_1, "22.438612mm 23.896105% auto auto").setReferenceComponents(gui_Label_1, "0 -1 -1 -1").setReferencePositions(gui_Label_1, "0.0 0.0 0.0 0.0");
    }// </editor-fold>
//-- DON'T EDIT ABOVE THIS LINE!!!
}
