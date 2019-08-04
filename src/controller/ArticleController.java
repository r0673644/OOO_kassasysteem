package controller;

import view.ArticleOverviewPane;
import view.Pane;

import java.util.ArrayList;
import java.util.List;

public class ArticleController extends Controller{
    private ArticleOverviewPane articleOverviewPane;
    private Pane pane;

    private List<String> statements;

    public ArticleController() {
        statements = new ArrayList<>();
        pane = new Pane();
    }

    public void setArticleOverviewPane(ArticleOverviewPane articleOverviewPane){
        this.articleOverviewPane = articleOverviewPane;
    }
    public ArticleOverviewPane getArticleOverviewPane(){
        return this.articleOverviewPane;
    }
    public view.Pane getPane() {
        return pane;
    }
    public List<String> getStatements() {
        return statements;
    }
    public void setStatements(List<String> statements) {
        this.statements = statements;
    }
    @Override
    protected Controller getController() {
        return this;
    }
}
