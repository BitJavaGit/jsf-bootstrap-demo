package de.bit.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Controller for switching between tabs
 * 
 * @author philipp.bayer@bridging-it.de
 */
@Controller
@Scope(value = "view")
public class TabController {

    public String activeTab = "day";

    public String getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(final String activeTab) {
        this.activeTab = activeTab;
    }

}
