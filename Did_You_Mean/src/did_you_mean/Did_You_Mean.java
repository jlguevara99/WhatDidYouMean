/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package did_you_mean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author HP
 */
public class Did_You_Mean {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ping = "[pol][ioujk][nbmhj][ghytfvb]";
        String traceroute = "[trfgy][redfgt][as][cvx][erwd][redfgt][oiklp][uyihj][trgfy][ewsdr]";
        String ls = "[loikp][sawedxz]";

        System.out.println("Comands: \n"
                + "ping: tests if a host is reachable across an IP network.\n"
                + "     Use: ping [IP or domain name]\n"
                + "traceroute: tracks the path a package on an IP network.\n"
                + "     Use: ping [IP or domain name]\n"
                + "ls: lists the directories and files in the current directory.\n"
        );
        String comando = "", respuesta = "";
        ArrayList<String> com = new ArrayList();
        do {
            System.out.print("What_Did_You_Mean> ");
            comando = sc.nextLine();
            String[] tokens = comando.split(" ");
            if (tokens[0].matches(ping)) {
                String record = leerArchivo("ping.txt");
                String[] tok = record.split(",");
                boolean exist = false;
                for (String tok1 : tok) {
                    if (tok1.equals(tokens[0])) {
                        exist = true;
                    }
                }
                if (exist) {
                    if (tokens.length > 1) {
                        ping(tokens[1]);
                    } else {
                        System.out.println("Use: ping [ip or domain name]");
                    }
                } else {
                    System.out.println("¿Did you mean 'ping'? [yes/no]");
                    respuesta = sc.nextLine();
                    if (respuesta.equals("yes")) {
                        escribirArchivo(tokens[0] + ",", "ping.txt");
                        if (tokens.length > 1) {
                            ping(tokens[1]);
                        } else {
                            System.out.println("Use: ping [ip or domain name]");
                        }
                    }
                }

            } else if (tokens[0].matches(traceroute)) {
                String record = leerArchivo("traceroute.txt");
                String[] tok = record.split(",");
                boolean exist = false;
                for (String tok1 : tok) {
                    if (tok1.equals(tokens[0])) {
                        exist = true;
                    }
                }
                if (exist) {
                    if (tokens.length > 1) {
                        traceroute(tokens[1]);
                    } else {
                        System.out.println("Use: ping [ip or domain name]");
                    }
                } else {
                    System.out.println("¿Did you mean 'traceroute'? [yes/no]");
                    respuesta = sc.nextLine();
                    if (respuesta.equals("yes")) {
                        escribirArchivo(tokens[0] + ",", "traceroute.txt");
                        if (tokens.length > 1) {
                            traceroute(tokens[1]);
                        } else {
                            System.out.println("Use: ping [ip or domain name]");
                        }
                    }
                }
            } else if (tokens[0].matches(ls)) {
                String record = leerArchivo("ls.txt");
                String[] tok = record.split(",");
                boolean exist = false;
                for (String tok1 : tok) {
                    if (tok1.equals(tokens[0])) {
                        exist = true;
                    }
                }
                if (exist) {
                    ls();
                } else {
                    System.out.println("¿Did you mean 'ls'? [yes/no]");
                    respuesta = sc.nextLine();
                    if (respuesta.equals("yes")) {
                        escribirArchivo(tokens[0] + ",", "ls.txt");
                        ls();
                    }
                }
            }
        } while (!"exit".equals(comando));

    }

    public static void ping(String p) {
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Run this on Windows, cmd, /c = terminate after this run
        processBuilder.command("cmd.exe", "/c", "ping -n 3 " + p);

        try {

            Process process = processBuilder.start();

            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void traceroute(String p) {
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Run this on Windows, cmd, /c = terminate after this run
        processBuilder.command("cmd.exe", "/c", "tracert -h 3 " + p);

        try {

            Process process = processBuilder.start();

            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void ls() {
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Run this on Windows, cmd, /c = terminate after this run
        processBuilder.command("cmd.exe", "/c", "dir");

        try {

            Process process = processBuilder.start();

            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void escribirArchivo(String v, String file) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src/" + file, true);
            pw = new PrintWriter(fichero);
            pw.print(v);
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                //e2.printStackTrace();
            }
        }
    }

    public static String leerArchivo(String file) {
        File fichero = new File("src/" + file);
        String ret = "";
        FileReader fr = null;
        BufferedReader bf = null;
        try {
            fr = new FileReader(fichero);
            bf = new BufferedReader(fr);
            String linea;
            while ((linea = bf.readLine()) != null) {
                ret += linea;
            }
        } catch (Exception e) {
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
            }
        }
        return ret;
    }

}
