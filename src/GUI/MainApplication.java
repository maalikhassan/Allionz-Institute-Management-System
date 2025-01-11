/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.*;

public class MainApplication {

    public static void main(String[] args) {
        // Show the loading screen
        
        FlatMacLightLaf.setup();
        loadingScreen loadingScreen = new loadingScreen();
        loadingScreen.setVisible(true);

        // Perform loading tasks in a background thread
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                // Simulate loading tasks
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(50); // Simulate time-consuming task
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publish(i); // Update progress
                }
                return null;
            }

            
            @Override
            protected void process(java.util.List<Integer> chunks) {
                // Update progress bar
                int progress = chunks.get(chunks.size() - 1);
                loadingScreen.updateProgress(progress);
            }

            @Override
            protected void done() {
                loadingScreen.dispose(); // Close the loading screen
                SwingUtilities.invokeLater(() -> new userSelection().setVisible(true)); // Open userSelection
            }

        };

        worker.execute();
    }
}
