package com.example.syncronote.logic.utilities;

import com.example.syncronote.logic.beans.EmailBean;
import com.example.syncronote.logic.exceptions.EmailSenderException;
import com.example.syncronote.logic.patterns.Observer;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailSenderBoundary extends AbsAlertGenerator implements Observer {
    private final String professor;
    private final String professorEmail;
    private static final String EMAIL_FILE = "EmailSentFile.txt";
    private final EmailBean emailData;

    public EmailSenderBoundary(String professor, String professorEmail, EmailBean emailData) throws EmailSenderException {
        checkFile();
        this.professor = professor;
        this.professorEmail = professorEmail;
        this.emailData = emailData;
    }

    @Override
    public void update() {

        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("EmailSentFile.txt")))) {

            checkFile();
            printWriter.println("Professor username: " + professor);
            printWriter.println("Professor username: " + professorEmail);
            printWriter.println("Student username: " + emailData.getStudent());
            printWriter.println("Student username: " + emailData.getStudentEmail());
            printWriter.println("Note name: " + emailData.getNoteName());
            printWriter.println("Answer: " + emailData.getStudentAnswer());
            printWriter.println("Question: " + emailData.getProfessorResponse());

            for (int i = 0; i < 40; i++)
                printWriter.print("-");

            printWriter.print("\n\n");

        }
        catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.INFO,e.getMessage());
        }
        catch (EmailSenderException e) {
            showErrorAlert("Email Error", e.getMessage());
        }

    }

    private boolean checkFile() throws EmailSenderException {
        File f = new File(EMAIL_FILE);

        if(!f.exists())
            throw new EmailSenderException("File doesn't exists");

        if(!f.canWrite())
            throw new EmailSenderException("Cannot write on file");

        return true;
    }
}
