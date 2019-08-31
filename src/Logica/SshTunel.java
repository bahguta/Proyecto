/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import javax.swing.JOptionPane;

/**
 *
 * @author Plam
 */
public class SshTunel {

    public void go() throws Exception {

        try {

            //datos para connectar al servidor
            //String host = "bahguta.ddns.net";
            String host = "bahguta.ddns.net";
            int port = 8204;
            //datos para rsa key 
            String user = "plamen";
            String password = "lala8204";
            String SshKeyFilepath = "lib\\id_rsa_plamen";
            //redireccion de puerto
            int tunnelLocalPort = 5656;
            //String tunnelRemoteHost = "bahguta.ddns.net";
            String tunnelRemoteHost = "bahguta.ddns.net";
            int tunnelRemotePort = 1521;

            System.out.println("Creando SSH Tunel");
            System.out.println("-------------------");
            JSch jsch = new JSch();
            try {
                jsch.addIdentity(SshKeyFilepath, password);
            } catch (JSchException e) {
                JOptionPane.showMessageDialog(null, "Key File NOT Found");
            }
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            Session session = jsch.getSession(user, host, port);
            session.setConfig(config);
            localUserInfo lui = new localUserInfo();
            session.setUserInfo(lui);
            System.out.println("Connecting ...");
            
            session.connect();
            if (session.isConnected()) {
                System.out.println("RSA key aceptado !");
                System.out.println("-------------------");
                System.out.println("SSH Tunel Creado !");

                System.out.println(session.getHost() + ":" + session.getPort());
                System.out.println("-------------------");
                int assinged_port = session.setPortForwardingL(tunnelLocalPort, tunnelRemoteHost, tunnelRemotePort);
 
                System.out.println("Puerto redireccionado con exito ! " + assinged_port);
                System.out.println("localhost:" + tunnelLocalPort + " -> " + tunnelRemoteHost + ":" + tunnelRemotePort);
            }
        } catch (JSchException e){
            
        }
    }

    class localUserInfo implements UserInfo {

        String passwd;

        @Override
        public String getPassword() {
            return passwd;
        }

        @Override
        public boolean promptYesNo(String str) {
            return true;
        }

        @Override
        public String getPassphrase() {
            return null;
        }

        @Override
        public boolean promptPassphrase(String message) {
            return true;
        }

        @Override
        public boolean promptPassword(String message) {
            return true;
        }

        @Override
        public void showMessage(String message) {
        }
    }

}
