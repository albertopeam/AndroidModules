package apa.accessmodule.ui.navigation;

/**
 * Created by alberto on 11/2/16.
 */
public interface Page<Params> {
    void backPage();
    void nextPage();
    void gotoPage(Params params);
}
