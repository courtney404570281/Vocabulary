package com.tom.vocabulary.game;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tom.vocabulary.R;
import com.tom.vocabulary.data.DataRepository;
import com.tom.vocabulary.data.Word;

import java.util.List;
import java.util.Random;

public class GameViewModel extends ViewModel {
    private final DataRepository repository;
    private LiveData<List<Word>> mWords = null;
    private final MediatorLiveData<List<Word>> wordsMediator = new MediatorLiveData<>();
    private final MutableLiveData<Word> currentWord = new MutableLiveData<>();
    private final MutableLiveData<Result> mResult = new MutableLiveData<>();

    private int correctScore = 0;
    private int inCorrectScore = 0;
    private int limitWord;

    public GameViewModel(DataRepository repository, int limitWord) {
        this.repository = repository;
        this.limitWord = limitWord;
    }

    public MutableLiveData<Word> getCurrentWord() {
        return currentWord;
    }

    public MutableLiveData<Result> getResult() {
        return mResult;
    }

    private LiveData<List<Word>> loadWords() {
        mWords = repository.getRandomWords(limitWord);
        return mWords;
    }

    public MediatorLiveData<List<Word>> setupGame() {
        if (mWords == null) {
            wordsMediator.addSource(loadWords(), wordsMediator::setValue);
        }
        return wordsMediator;
    }

    public void resetGame() {
        wordsMediator.removeSource(mWords);
        wordsMediator.addSource(loadWords(), wordsMediator::setValue);
        correctScore = 0;
        inCorrectScore = 0;
    }

    public void newGameRound() {
        if ((correctScore + inCorrectScore) == 3)
            return;
        Random random = new Random();
        List<Word> words = mWords.getValue();
        if (words == null || words.size() == 0) {
            return;
        }
        int answer = random.nextInt(words.size());
        Word word = words.get(answer);
        currentWord.setValue(word);
    }

    public void updateResult(String answer) {
        Word word = currentWord.getValue();
        if (word == null) {
            return;
        }
        Result result;
        if (answer.equals(word.getName())) {
            correctScore++;
            result = new Result(R.color.colorCorrect, R.string.correct_answer);
        } else {
            inCorrectScore++;
            result = new Result(R.color.colorWrong, R.string.wrong_answer);
        }
        if ((correctScore+inCorrectScore) != 3) {
            newGameRound();
            mResult.setValue(result);
        } else {
            goodGame();
        }
    }

    private void goodGame() {
        Result result;
        if (correctScore == 3) {
            result = new Result(R.color.colorCorrect, R.string.win, false);
        } else if (correctScore == 2) {
            result = new Result(R.color.colorAlmost, R.string.maybe, false);
        } else {
            result = new Result(R.color.colorWrong, R.string.loose, false);
        }
        mResult.setValue(result);
    }
}
