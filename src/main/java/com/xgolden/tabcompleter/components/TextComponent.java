package com.xgolden.tabcompleter.components;

public class TextComponent {

    private StringBuilder sb;

    public TextComponent() {
        sb = new StringBuilder();
    }

    public TextComponent(String str) {
        sb = new StringBuilder().append(str);
    }

    public void append(String s) {
        sb.append(" ").append(s);
    }

    public String getString() {
        return sb.toString();
    }
    
}

