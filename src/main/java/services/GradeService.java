package services;

import entities.User;

public class GradeService {



    //SUPER HARD
    //De datum staat automatisch op vandaag
    //de grade mag niet minder dan 0 zijn, en mag niet meer zijn dan de punten van het examen
    //Je gaat een Exam moeten oproepen van de bestaande lijst van examens, Eman mag dus niet op null staan
    //Je gaat een Person moeten terugvinden met User
    public void createGrade(User user){

    }

    //EASY
    public void getOneGradeById(){

    }

    //MEDIUM
    //Controleer eerst of de user niet 'null' is
    //Gebruik een user.getPerson methode, en maak een extra methode in je DAO/repository om resultaten op te vragen met person
    public void getAllGradeByPerson(User user){

    }

    //HARD
    //Controleer eerst of de user niet 'null' is
    //vraag alle grades op van een Person en kies de Grade die je wilt aanpassen
    //Enkel de gradeValue en de comment mogen aangepast worden
    //de grade mag niet minder dan 0 zijn, en mag niet meer zijn dan de punten van het examen
    public void updateGrade(User user){

    }

    //EASY
    //Controleer eerst of de user niet 'null' is
    //vraag alle grades op van een Person en kies de Grade die je wilt aanpassen
    public void deleteGrade(User user){

    }

    //----
    //extra private methodes hieronder
}
