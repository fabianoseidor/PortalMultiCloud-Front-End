package br.com.portal.servise;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

import br.com.portal.model.loginunificado.Users;



public class UserService {
	static int codigoSucesso = 200;
	
     
    public static Users buscaUserLogin( String webService) throws Exception {
        try {
            String jsonEmString = doGet(webService);

            Gson gson = new Gson();
            Users users =  gson.fromJson(jsonEmString, Users.class);

            return users;
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }
    
    
    /**
     * HttpURLConnection GET
     * @param urlString
     * @return
     */
    public static String doGet(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return readStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    } 
    
    
    

    public static String updatePassoword(String login, String pass, Long idPessoa, String webService) throws Exception {
    	
    	
        String urlParaChamada = webService + "updadePass/" + login + "/" + pass + "/" + idPessoa;

        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setReadTimeout(5000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setDoOutput(true);

                int responseCode = urlConnection.getResponseCode();

                if (responseCode == 200) {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    return readStream(in);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }	
    
    private static String readStream(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
//        System.out.println(sb.toString());
        br.close();
        return sb.toString();
    }    
    

}
