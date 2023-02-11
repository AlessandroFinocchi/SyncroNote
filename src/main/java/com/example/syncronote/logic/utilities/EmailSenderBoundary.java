package com.example.syncronote.logic.utilities;

import com.example.syncronote.logic.beans.PublicationProfessorBean;
import com.example.syncronote.logic.exceptions.EmailSenderException;
import com.example.syncronote.logic.patterns.Observer;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailSenderBoundary extends AbsDialogNavigatorController implements Observer {
    private PublicationProfessorBean publicationProfessorBean;
    private static final String EMAIL_FILE = "EmailSentFile.txt";

    public EmailSenderBoundary(PublicationProfessorBean publicationProfessorBean) throws EmailSenderException {
        checkFile();
        this.publicationProfessorBean = publicationProfessorBean;
    }

    @Override
    public void update() {

        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(EMAIL_FILE)))) {

            checkFile();

            for (String email: publicationProfessorBean.getSubscribedEmails()) {
                printWriter.println("Email sent from: " + publicationProfessorBean.getProfessorEmail());
                printWriter.println("Email sent to: " + email);
                printWriter.println("Professor username: " + publicationProfessorBean.getProfessor());
                printWriter.println("Has published note name: " + publicationProfessorBean.getTitle());

                for (int i = 0; i < 40; i++)
                    printWriter.print("-");

                printWriter.print("\n\n");
            }

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
