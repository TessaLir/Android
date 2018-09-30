package ru.home.vetukov.test.calculator;

public class BottonLine {

    private Botton bottonFirst;
    private Botton bottonSecond;
    private Botton bottonThird;
    private Botton bottonFourth;
    private Botton bottonFifth;

    public BottonLine(Botton bottonFirst
                     ,Botton bottonSecond
                     ,Botton bottonThird
                     ,Botton bottonFourth
                     ,Botton bottonFifth)
    {
        this.bottonFirst = bottonFirst;
        this.bottonSecond = bottonSecond;
        this.bottonThird = bottonThird;
        this.bottonFourth = bottonFourth;
        this.bottonFifth = bottonFifth;
    }

    public Botton getBottonFirst() {
        return bottonFirst;
    }

    public Botton getBottonSecond() {
        return bottonSecond;
    }

    public Botton getBottonThird() {
        return bottonThird;
    }

    public Botton getBottonFourth() {
        return bottonFourth;
    }

    public Botton getBottonFifth() {
        return bottonFifth;
    }
}
