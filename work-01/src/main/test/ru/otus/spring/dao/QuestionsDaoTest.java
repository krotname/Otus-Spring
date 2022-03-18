package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionsDaoTest {

    @Test
    void getQuestions() throws IOException {
        QuestionsDao questionsDao = new QuestionsDao();
        List<Question> questions = questionsDao.getQuestions();

        assertEquals(questions.toString(), "[Question(questions=What was the name of a warrior, warrior in Ancient Russia?, answer=Warrior), Question(questions=What event happened in Russia in 988?, answer=Baptism of Russia), Question(questions=To which prince do we owe the appearance of the modern Kremlin?, answer=Ivan III), Question(questions=What was the name of the main god of the Slavs?, answer=Perun), Question(questions=A place where people live, surrounded by a fence., answer=Settlement), Question(questions=Bast shoes of Russian peasants on onuchs., answer=Bast shoes), Question(questions=Old peasant outerwear without buttons, which, due to poverty, was girded with a rein., answer=Armyak), Question(questions=The old way of conquering fortresses, cities (synonymous with the word \"blockade\")., answer=Izmor), Question(questions=In what city of Ancient Russia did people's meetings take place, which were called \"veche\"?, answer=Novgorod), Question(questions=On the banks of what river was Novgorod built?, answer=Volkhov), Question(questions=From ancient times to the present day - the representative of his country abroad., answer=Ambassador), Question(questions=Who got the very first flags?, answer=At warriors), Question(questions=What were the names of the Swedish and German knights who were defeated by the troops of Alexander Nevsky during the Battle of the Ice?, answer=Crusaders), Question(questions=What was the name of the first owner of the land on which Moscow stands?, answer=Boyar Kuchka), Question(questions=Which two kings are currently in the Moscow Kremlin?, answer=Tsar Cannon and Tsar Bell), Question(questions=Which king ordered the execution of the elephant and for what?, answer=Ivan IV), Question(questions=What was the name of the first printed book in Russia?, answer=Apostle), Question(questions=What land did Yermak discover?, answer=Siberia), Question(questions=Who is the founder of Moscow?, answer=Yuri Dolgoruky), Question(questions=Specialist involved in the study of the life and culture of ancient peoples on the basis of surviving monuments., answer=Archaeologist)]");
    }
}