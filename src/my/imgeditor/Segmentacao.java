/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.imgeditor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.swing.JLabel;

/**
 *
 * @author Iverton Perboyre L. Maia
 */
public class Segmentacao {
    
    public static BufferedImage SDGM(BufferedImage entrada){                    //CHAMADA AO METODO S-DGM() COM UMA IMAGEM DE ENTRADA.
        
        /**
         * O metodo S-DGM() realiza a segmentacao da imagem de entrada utilizando difusao geometrica Markoviana (DGM).
         * A DGM atribui importancias aos pixels da imagem, o que permite que o S-DGM elimine pixels pouco relevantes
         * para as formas dos objetos presentes na imagem.
         */
                
        BufferedImage img1 = getImage1(0.59);                                   //PRIMEIRA AMOSTRA DE IMAGEM.
        BufferedImage img2 = getImage2(0.66);                                   //SEGUNDA AMOSTRA DE IMAGEM.
        img1 = removeNoise(img1, img2);                                         //REMOÇÃO DE RUIDOS NA IMAGEM PROCESSADA.
        //img1 = setBlank(img1);
        
        return img1;                                                          //IMAGEM FINAL.
    }
    
    public static BufferedImage removeNoise(BufferedImage entrada1, BufferedImage entrada2){
        
        /**
         *O metodo removeNoise() minimiza ruidos na imagem final, realizando uma combinacao entre duas amostras de imagem
         * geradas pelas duas etapas iniciais do metodo S-DGM().
         * Os ruidos são reduzidos, mas não eliminados totalmente da imagem.
         */
        
        BufferedImage img1 = new BufferedImage(entrada1.getWidth(), entrada1.getHeight(), BufferedImage.TYPE_INT_RGB);
        BufferedImage img2 = new BufferedImage(entrada2.getWidth(), entrada2.getHeight(), BufferedImage.TYPE_INT_RGB);
        aux = new boolean[entrada1.getHeight()][entrada1.getWidth()];          //MATRIZ AUXILIAR PARA MANTER COPIA DE matRemoved1[][].
        
        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0; j < img1.getWidth(); j ++){
                
                aux[i][j] = matRemoved1[i][j];                                 //COPIA DE matRemoved1[][] EM aux[][].
                
                if(matRemoved1[i][j] == true){                                 //SOMENTE SÃO COPIADOS OS PIXELS QUE NAO FORAM ELIMINADOS NA CHAMADA DE getImage1().
                    Color c = new Color(entrada1.getRGB(j, i));
                    img1.setRGB(j, i, c.getRGB());
                }
                
                if(matRemoved2[i][j] == true){                                 //SOMENTE SÃO COPIADOS OS PIXELS QUE NAO FORAM ELIMINADOS NA CHAMADA DE getImage2().
                    Color c = new Color(entrada2.getRGB(j, i));
                    img2.setRGB(j, i, c.getRGB());
                }
            }
        }
        
        for(int i = 0; i < img2.getHeight(); i ++){
            for(int j = 0; j < img2.getWidth(); j ++){
                if(matRemoved2[i][j] == true){                                 
                    aux[i][j] = false;                                         //MARCA COMO false EM aux[][] OS PIXELS QUE "SOBRAM" EM img2 E FALTAM EM img1.
                }                                                              //COMO img2 POSSUI MENOS PIXELS ATIVOS QUE img1, NÃO EXISTE RISCO DE ELIMINAÇÃO INDEVIDA.
            }
        }
        
        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0; j < img1.getWidth(); j ++){
                if(aux[i][j] == true){
                    Color c = new Color(0, 0, 0);
                    img1.setRGB(j, i, c.getRGB());
                }           
            }
        }
        
        for(int i = 2; i < img1.getHeight() - 2; i ++){
            for(int j = 2; j < img1.getWidth() - 2; j ++){
                
                Color c;
                int cont = 0;
                
                for(int a = i - 2; a < i + 2; a ++){
                    for(int b = j - 2; b < j + 2; b ++){
                        
                        Color c1 = new Color(img1.getRGB(b, a));
                        if(c1.getRed() != 0) cont ++;
                    }
                }
                
                if(cont < 6){                                                  //ELIMINA PIXELS QUE POSSUEM MENOS DE 6 VIZINHOS.
                    c = new Color(0, 0, 0);
                    img1.setRGB(j, i, c.getRGB());
                }
            }
        }
        
        return img1;
    }
    
    private static BufferedImage getImage1(double valor){
        
        BufferedImage img1 = RGB.TomDeCinzaSDGM();                       //IMAGEM E' TRANSFORMADA EM ESCALA DE CINZA.
        matRemoved1 = new boolean[img1.getHeight()][img1.getWidth()];           //INICIA-SE A MATRIZ BOOLEANA PARA MAPEAR QUEM SERA' REMOVIDO DA IMAGEM.
        
        for(int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i ++){
            for(int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j ++){
                matRemoved1[i][j] = true;
            }
        }
                    
        setImportance1(img1);                                                   //CALCULA-SE E ATRIBUI-SE AS IMPORTÂNCIAS DOS PIXELS.
        
        int tam = 0;
        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0; j < img1.getWidth(); j ++){
                tam ++;                                                         
            }                    
        }       

        double[] vet = new double[tam];                                         //CRIA-SE UM VETOR DO TAMANHO DA QUANTIDADE DE PIXELS DA IMAGEM.
        tam = 0;                                                                
        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0; j < img1.getWidth(); j ++){
                if(matRemoved1[i][j] == true){
                    vet[tam] = matImportancia[i][j];                            //COPIA-SE AS IMPORTÂNCIAS DA MATRIZ PARA O VETOR.
                    tam ++;
                }
            }
        }

        Arrays.sort(vet);
        int percentil = (int)(tam*valor);                                       //CALCULA-SE O PERCENTIL EM FUNÇÃO DO PARÂMETRO valor
                                                                                //E DA QUANTIDADE DE PIXELS ATIVOS NA IMAGEM.
        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0; j < img1.getWidth(); j ++){                   

                if(i == 0 && j == 0){                                           // CASO 1                        
                    if(matRemoved1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved1[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            matRemoved1[i][j] = false;
                        }
                        else{                            
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved1[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == 0 && j > 0 && j < img1.getWidth() - 1){            // CASO 2
                    if(matRemoved1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved1[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            matRemoved1[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved1[i][j] = false;
                            }
                        }
                    }                        
                }

                else if(i == 0 && j == img1.getWidth() - 1){                    // CASO 3
                    if(matRemoved1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved1[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            matRemoved1[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved1[i][j] = false;
                            }
                        }
                    }
                }

                else if(j == 0 && i > 0 && i < img1.getHeight() - 1){           // CASO 4
                    if(matRemoved1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved1[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            matRemoved1[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved1[i][j] = false;
                            }
                        }
                    }                                              
                }

                else if(j == img1.getWidth() - 1 && i > 0 && i < img1.getHeight() - 1){     // CASO 5
                    if(matRemoved1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved1[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            matRemoved1[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved1[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == img1.getHeight() - 1 && j == 0){                   // CASO 6
                    if(matRemoved1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved1[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            matRemoved1[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved1[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == img1.getHeight() - 1 && j > 0 && j < img1.getWidth() - 1){     // CASO 7
                    if(matRemoved1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved1[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            matRemoved1[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved1[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == img1.getHeight() - 1 && j == img1.getWidth() - 1){     // CASO 8
                    if(matRemoved1[i][j] == true){
                        int qtdVizinhos = 0;
                        
                        if(matRemoved1[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            matRemoved1[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved1[i][j] = false;
                            }
                        }
                    }
                }

                else{                                                           // CASO GERAL
                    if(matRemoved1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved1[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved1[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }
                        
                        if(qtdVizinhos < 3){
                            matRemoved1[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved1[i][j] = false;
                            }
                        }
                    }
                }                                          
            }                    
        }

        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0 ; j < img1.getWidth(); j ++){
                if(matRemoved1[i][j] == false){
                    Color c = new Color(0, 0, 0);
                    img1.setRGB(j, i, c.getRGB());                              //ELIMINA-SE OS PIXELS MARCADOS PARA REMOÇÃO.                 
                }
            }
        }
        
        return img1;
    }
    
    private static BufferedImage getImage2(double valor){
        
        BufferedImage img1 = RGB.TomDeCinzaSDGM();
        matRemoved2 = new boolean[img1.getHeight()][img1.getWidth()];
        
        for(int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i ++){
            for(int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j ++){
                matRemoved2[i][j] = true;
            }
        }
        
        setImportance2(img1);
        int tam = 0;
        for(int a = 0; a < img1.getHeight(); a ++){
            for(int b = 0; b < img1.getWidth(); b ++){
                tam ++;
            }                    
        }       

        double[] vet = new double[tam];
        tam = 0;
        for(int a = 0; a < img1.getHeight(); a ++){
            for(int b = 0; b < img1.getWidth(); b ++){
                if(matRemoved2[a][b] == true){
                    vet[tam] = matImportancia[a][b];
                    tam ++;
                }
            }
        }

        Arrays.sort(vet);
        int percentil = (int)(tam*valor);

        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0; j < img1.getWidth(); j ++){                   

                if(i == 0 && j == 0){                        // CASO 1                        
                    if(matRemoved2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved2[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            matRemoved2[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved2[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == 0 && j > 0 && j < img1.getWidth() - 1){                         // CASO 2
                    if(matRemoved2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved2[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            matRemoved2[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved2[i][j] = false;
                            }
                        }
                    }                        
                }

                else if(i == 0 && j == img1.getWidth() - 1){                         // CASO 3
                    if(matRemoved2[i][j] == true){
                        int qtdVizinhos = 0;
                        
                        if(matRemoved2[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            matRemoved2[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved2[i][j] = false;
                            }
                        }
                    }
                }

                else if(j == 0 && i > 0 && i < img1.getHeight() - 1){                            // CASO 4
                    if(matRemoved2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved2[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            matRemoved2[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved2[i][j] = false;
                            }
                        }
                    }                                              
                }

                else if(j == img1.getWidth() - 1 && i > 0 && i < img1.getHeight() - 1){                           // CASO 5
                    if(matRemoved2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved2[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            matRemoved2[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved2[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == img1.getHeight() - 1 && j == 0){                            // CASO 6
                    if(matRemoved2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved2[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            matRemoved2[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved2[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == img1.getHeight() - 1 && j > 0 && j < img1.getWidth() - 1){                           // CASO 7
                    if(matRemoved2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved2[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i - 1][j + 1] == true){
                        }

                        if(matRemoved2[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            matRemoved2[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved2[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == img1.getHeight() - 1 && j == img1.getWidth() - 1){                           // CASO 8
                    if(matRemoved2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved2[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            matRemoved2[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved2[i][j] = false;
                            }
                        }
                    }
                }

                else{                           // CASO GERAL
                    if(matRemoved2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(matRemoved2[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(matRemoved2[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }                        
                        if(qtdVizinhos < 3){
                            matRemoved2[i][j] = false;
                        }
                        else{
                            if(matImportancia[i][j] < vet[percentil]){
                                matRemoved2[i][j] = false;
                            }
                        }
                    }
                }                                          
            }                    
        }

        for(int a = 0; a < img1.getHeight(); a ++){
            for(int b = 0 ; b < img1.getWidth(); b ++){
                if(matRemoved2[a][b] == false){
                    Color c = new Color(0, 0, 0);
                    img1.setRGB(b, a, c.getRGB());
                }
            }
        }            
        
        return img1;
    }
    
    private static void setImportance1(BufferedImage entrada){
        
        int qtdVizinhos = 0;        
        matImportancia = new double[entrada.getHeight()][entrada.getWidth()];       //MATRIZ DE double PARA ARMAZENAR AS IMPROTÂNCIAS DE CADA PIXEL.
        
        for(int i = 0; i < entrada.getHeight(); i ++){
            for(int j = 0; j < entrada.getWidth(); j ++){
                
                double soma = 0.0;
                
                if(i == 0 && j == 0){                           // CASO 1                    
                    if(matRemoved1[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved1[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));          //NUCLEO DA DGM E' CALCULADO
                            qtdVizinhos ++;                                                         //EM FUNÇÃO DA DISTÂNCIA ENTRE AS CORES DOS PIXELS.
                        }
                        if(matRemoved1[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));                           //IMPORTÂNCIA E' NORMALIZADA E
                        matImportancia[i][j] = importancia;                                        //ARMAZENADA NA MATRIZ.
                        qtdVizinhos = 0;
                    }
                }
                else if(i == 0 && j > 0 && j < entrada.getWidth() - 1){                         // CASO 2                    
                    if(matRemoved1[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved1[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == 0 && j == entrada.getWidth() - 1){                         // CASO 3                    
                    if(matRemoved1[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved1[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(j == 0 && i > 0 && i < entrada.getHeight() - 1){                            // CASO 4                    
                    if(matRemoved1[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved1[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(j == entrada.getWidth() - 1 && i > 0 && i < entrada.getHeight() - 1){                           // CASO 5
                    if(matRemoved1[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved1[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == entrada.getHeight() - 1 && j == 0){                            // CASO 6
                    if(matRemoved1[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved1[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == entrada.getHeight() - 1 && j > 0 && j < entrada.getWidth() - 1){                           // CASO 7
                    if(matRemoved1[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved1[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == entrada.getHeight() - 1 && j == entrada.getWidth() - 1){                           // CASO 8
                    if(matRemoved1[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved1[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else{                           // CASO GERAL
                    if(matRemoved1[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved1[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved1[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
            }
        }
    }
    
    private static void setImportance2(BufferedImage entrada){
        
        int qtdVizinhos = 0;
        matImportancia = new double[entrada.getHeight()][entrada.getWidth()];
        
        for(int i = 0; i < entrada.getHeight(); i ++){
            for(int j = 0; j < entrada.getWidth(); j ++){
                
                double soma = 0.0;
                
                if(i == 0 && j == 0){                           // CASO 1                    
                    if(matRemoved2[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved2[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == 0 && j > 0 && j < entrada.getWidth() - 1){                         // CASO 2                    
                    if(matRemoved2[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved2[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == 0 && j == entrada.getWidth() - 1){                         // CASO 3                    
                    if(matRemoved2[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved2[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(j == 0 && i > 0 && i < entrada.getHeight() - 1){                            // CASO 4                    
                    if(matRemoved2[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved2[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(j == entrada.getWidth() - 1 && i > 0 && i < entrada.getHeight() - 1){                           // CASO 5
                    if(matRemoved2[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved2[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == entrada.getHeight() - 1 && j == 0){                            // CASO 6
                    if(matRemoved2[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved2[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == entrada.getHeight() - 1 && j > 0 && j < entrada.getWidth() - 1){                           // CASO 7
                    if(matRemoved2[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved2[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == entrada.getHeight() - 1 && j == entrada.getWidth() - 1){                           // CASO 8
                    if(matRemoved2[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved2[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else{                           // CASO GERAL
                    if(matRemoved2[i][j] == true){
                        
                        Color c = new Color(entrada.getRGB(j, i));
                        
                        if(matRemoved2[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(matRemoved2[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matImportancia[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
            }
        }
    }
    
    private static BufferedImage setBlank(BufferedImage entrada){
        
        BufferedImage img1 = new BufferedImage(entrada.getWidth(), entrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0; j < img1.getWidth(); j ++){
                Color c = new Color(entrada.getRGB(j, i));
                if(c.getRed() != 0) c = new Color(255, 255, 255);
                img1.setRGB(j, i, c.getRGB());
            }
        }
        
        return img1;
    }
    
    private static BufferedImage getModa(BufferedImage entrada){
        
        BufferedImage img1 = new BufferedImage(entrada.getWidth(), entrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for(int i = 0; i < entrada.getHeight(); i ++){
            for(int j = 0; j < entrada.getWidth(); j ++){
                
                Color c = new Color(entrada.getRGB(j, i));
                
                if(c.getRed() != 0){
                    
                    if(i == 0 && j == 0){                           // CASO 1
                    
                    int m0 = 0;
                    int m1 = 0;
                    int m2 = 0;
                    int m3 = 0;
                    Color c0 = new Color(entrada.getRGB(j, i));
                    Color c1 = new Color(entrada.getRGB(j + 1, i));
                    Color c2 = new Color(entrada.getRGB(j + 1, i + 1));
                    Color c3 = new Color(entrada.getRGB(j, i + 1));
                    
                    int[] vet = new int[4];
                    m0 ++;
                    
                    if(c0.getRed() == c1.getRed()){
                        m0 ++;
                        m1 ++;
                    }
                    if(c0.getRed() == c2.getRed()){
                        m0 ++;
                        m2 ++;
                    }
                    if(c0.getRed() == c3.getRed()){
                        m0 ++;
                        m3 ++;
                    }
                    if(c1.getRed() == c2.getRed()){
                        m1 ++;
                        m2 ++;
                    }
                    if(c1.getRed() == c3.getRed()){
                        m1 ++;
                        m3 ++;
                    }
                    if(c2.getRed() == c3.getRed()){
                        m2 ++;
                        m3 ++;
                    }
                    
                    vet[0] = m0; vet[1] = m1; vet[2] = m2; vet[3] = m3;
                    Arrays.sort(vet);
                    
                    if(vet[3] == m0){
                        c = new Color(c0.getRed(), c0.getRed(), c0.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[3] == m1){
                        c = new Color(c1.getRed(), c1.getRed(), c1.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[3] == m2){
                        c = new Color(c2.getRed(), c2.getRed(), c2.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[3] == m3){
                        c = new Color(c3.getRed(), c3.getRed(), c3.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                }
                else if(i == 0 && j > 0 && j < entrada.getWidth() - 1){                         // CASO 2
                    
                    int m0 = 0;
                    int m1 = 0;
                    int m2 = 0;
                    int m3 = 0;
                    int m4 = 0;
                    int m5 = 0;
                    Color c0 = new Color(entrada.getRGB(j, i));
                    Color c1 = new Color(entrada.getRGB(j + 1, i));
                    Color c2 = new Color(entrada.getRGB(j + 1, i + 1));
                    Color c3 = new Color(entrada.getRGB(j, i + 1));
                    Color c4 = new Color(entrada.getRGB(j - 1, i + 1));
                    Color c5 = new Color(entrada.getRGB(j - 1, i));
                    
                    int[] vet = new int[6];
                    m0 ++;
                    
                    if(c0.getRed() == c1.getRed()){
                        m0 ++;
                        m1 ++;
                    }
                    if(c0.getRed() == c2.getRed()){
                        m0 ++;
                        m2 ++;
                    }
                    if(c0.getRed() == c3.getRed()){
                        m0 ++;
                        m3 ++;
                    }
                    if(c0.getRed() == c4.getRed()){
                        m0 ++;
                        m4 ++;
                    }
                    if(c0.getRed() == c5.getRed()){
                        m0 ++;
                        m5 ++;
                    }
                    if(c1.getRed() == c2.getRed()){
                        m1 ++;
                        m2 ++;
                    }
                    if(c1.getRed() == c3.getRed()){
                        m1 ++;
                        m3 ++;
                    }
                    if(c1.getRed() == c4.getRed()){
                        m1 ++;
                        m4 ++;
                    }
                    if(c1.getRed() == c5.getRed()){
                        m1 ++;
                        m5 ++;
                    }
                    if(c2.getRed() == c3.getRed()){
                        m2 ++;
                        m3 ++;
                    }
                    if(c2.getRed() == c4.getRed()){
                        m2 ++;
                        m4 ++;
                    }
                    if(c2.getRed() == c5.getRed()){
                        m2 ++;
                        m5 ++;
                    }
                    if(c3.getRed() == c4.getRed()){
                        m3 ++;
                        m4 ++;
                    }
                    if(c3.getRed() == c5.getRed()){
                        m3 ++;
                        m5 ++;
                    }
                    if(c4.getRed() == c5.getRed()){
                        m4 ++;
                        m5 ++;
                    }
                    
                    vet[0] = m0; vet[1] = m1; vet[2] = m2;
                    vet[3] = m3; vet[4] = m4; vet[5] = m5;
                    Arrays.sort(vet);
                    
                    if(vet[5] == m0){
                        c = new Color(c0.getRed(), c0.getRed(), c0.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m1){
                        c = new Color(c1.getRed(), c1.getRed(), c1.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m2){
                        c = new Color(c2.getRed(), c2.getRed(), c2.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m3){
                        c = new Color(c3.getRed(), c3.getRed(), c3.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m4){
                        c = new Color(c4.getRed(), c4.getRed(), c4.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == 5){
                        c = new Color(c5.getRed(), c5.getRed(), c5.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                }
                else if(i == 0 && j == entrada.getWidth() - 1){                         // CASO 3
                    
                    int m0 = 0;
                    int m1 = 0;
                    int m2 = 0;
                    int m3 = 0;
                    Color c0 = new Color(entrada.getRGB(j, i));
                    Color c1 = new Color(entrada.getRGB(j, i + 1));
                    Color c2 = new Color(entrada.getRGB(j - 1, i + 1));
                    Color c3 = new Color(entrada.getRGB(j - 1, i));
                    
                    int[] vet = new int[4];
                    m0 ++;                    
                    
                    if(c0.getRed() == c1.getRed()){
                        m0 ++;
                        m1 ++;
                    }
                    if(c0.getRed() == c2.getRed()){
                        m0 ++;
                        m2 ++;
                    }
                    if(c0.getRed() == c3.getRed()){
                        m0 ++;
                        m3 ++;
                    }
                    if(c1.getRed() == c2.getRed()){
                        m1 ++;
                        m2 ++;
                    }
                    if(c1.getRed() == c3.getRed()){
                        m1 ++;
                        m3 ++;
                    }
                    if(c2.getRed() == c3.getRed()){
                        m2 ++;
                        m3 ++;
                    }
                    
                    vet[0] = m0; vet[1] = m1; vet[2] = m2; vet[3] = m3;
                    Arrays.sort(vet);
                    
                    if(vet[3] == m0){
                        c = new Color(c0.getRed(), c0.getRed(), c0.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[3] == m1){
                        c = new Color(c1.getRed(), c1.getRed(), c1.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[3] == m2){
                        c = new Color(c2.getRed(), c2.getRed(), c2.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[3] == m3){
                        c = new Color(c3.getRed(), c3.getRed(), c3.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                }
                else if(j == 0 && i > 0 && i < entrada.getHeight() - 1){                            // CASO 4
                    
                    int m0 = 0;
                    int m1 = 0;
                    int m2 = 0;
                    int m3 = 0;
                    int m4 = 0;
                    int m5 = 0;
                    Color c0 = new Color(entrada.getRGB(j, i));
                    Color c1 = new Color(entrada.getRGB(j, i - 1));
                    Color c2 = new Color(entrada.getRGB(j + 1, i - 1));
                    Color c3 = new Color(entrada.getRGB(j + 1, i));
                    Color c4 = new Color(entrada.getRGB(j + 1, i + 1));
                    Color c5 = new Color(entrada.getRGB(j, i + 1));
                    
                    int[] vet = new int[6];
                    m0 ++;
                    
                    if(c0.getRed() == c1.getRed()){
                        m0 ++;
                        m1 ++;
                    }
                    if(c0.getRed() == c2.getRed()){
                        m0 ++;
                        m2 ++;
                    }
                    if(c0.getRed() == c3.getRed()){
                        m0 ++;
                        m3 ++;
                    }
                    if(c0.getRed() == c4.getRed()){
                        m0 ++;
                        m4 ++;
                    }
                    if(c0.getRed() == c5.getRed()){
                        m0 ++;
                        m5 ++;
                    }
                    if(c1.getRed() == c2.getRed()){
                        m1 ++;
                        m2 ++;
                    }
                    if(c1.getRed() == c3.getRed()){
                        m1 ++;
                        m3 ++;
                    }
                    if(c1.getRed() == c4.getRed()){
                        m1 ++;
                        m4 ++;
                    }
                    if(c1.getRed() == c5.getRed()){
                        m1 ++;
                        m5 ++;
                    }
                    if(c2.getRed() == c3.getRed()){
                        m2 ++;
                        m3 ++;
                    }
                    if(c2.getRed() == c4.getRed()){
                        m2 ++;
                        m4 ++;
                    }
                    if(c2.getRed() == c5.getRed()){
                        m2 ++;
                        m5 ++;
                    }
                    if(c3.getRed() == c4.getRed()){
                        m3 ++;
                        m4 ++;
                    }
                    if(c3.getRed() == c5.getRed()){
                        m3 ++;
                        m5 ++;
                    }
                    if(c4.getRed() == c5.getRed()){
                        m4 ++;
                        m5 ++;
                    }
                    
                    vet[0] = m0; vet[1] = m1; vet[2] = m2;
                    vet[3] = m3; vet[4] = m4; vet[5] = m5;
                    Arrays.sort(vet);
                    
                    if(vet[5] == m0){
                        c = new Color(c0.getRed(), c0.getRed(), c0.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m1){
                        c = new Color(c1.getRed(), c1.getRed(), c1.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m2){
                        c = new Color(c2.getRed(), c2.getRed(), c2.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m3){
                        c = new Color(c3.getRed(), c3.getRed(), c3.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m4){
                        c = new Color(c4.getRed(), c4.getRed(), c4.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == 5){
                        c = new Color(c5.getRed(), c5.getRed(), c5.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                }
                else if(j == entrada.getWidth() - 1 && i > 0 && i < entrada.getHeight() - 1){                           // CASO 5
                    
                    int m0 = 0;
                    int m1 = 0;
                    int m2 = 0;
                    int m3 = 0;
                    int m4 = 0;
                    int m5 = 0;
                    Color c0 = new Color(entrada.getRGB(j, i));
                    Color c1 = new Color(entrada.getRGB(j, i - 1));
                    Color c2 = new Color(entrada.getRGB(j, i + 1));
                    Color c3 = new Color(entrada.getRGB(j - 1, i + 1));
                    Color c4 = new Color(entrada.getRGB(j - 1, i));
                    Color c5 = new Color(entrada.getRGB(j - 1, i - 1));
                    
                    int[] vet = new int[6];
                    
                    if(c0.getRed() == c1.getRed()){
                        m0 ++;
                        m1 ++;
                    }
                    if(c0.getRed() == c2.getRed()){
                        m0 ++;
                        m2 ++;
                    }
                    if(c0.getRed() == c3.getRed()){
                        m0 ++;
                        m3 ++;
                    }
                    if(c0.getRed() == c4.getRed()){
                        m0 ++;
                        m4 ++;
                    }
                    if(c0.getRed() == c5.getRed()){
                        m0 ++;
                        m5 ++;
                    }
                    if(c1.getRed() == c2.getRed()){
                        m1 ++;
                        m2 ++;
                    }
                    if(c1.getRed() == c3.getRed()){
                        m1 ++;
                        m3 ++;
                    }
                    if(c1.getRed() == c4.getRed()){
                        m1 ++;
                        m4 ++;
                    }
                    if(c1.getRed() == c5.getRed()){
                        m1 ++;
                        m5 ++;
                    }
                    if(c2.getRed() == c3.getRed()){
                        m2 ++;
                        m3 ++;
                    }
                    if(c2.getRed() == c4.getRed()){
                        m2 ++;
                        m4 ++;
                    }
                    if(c2.getRed() == c5.getRed()){
                        m2 ++;
                        m5 ++;
                    }
                    if(c3.getRed() == c4.getRed()){
                        m3 ++;
                        m4 ++;
                    }
                    if(c3.getRed() == c5.getRed()){
                        m3 ++;
                        m5 ++;
                    }
                    if(c4.getRed() == c5.getRed()){
                        m4 ++;
                        m5 ++;
                    }
                    
                    vet[0] = m0; vet[1] = m1; vet[2] = m2;
                    vet[3] = m3; vet[4] = m4; vet[5] = m5;
                    Arrays.sort(vet);
                    
                    if(vet[5] == m0){
                        c = new Color(c0.getRed(), c0.getRed(), c0.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m1){
                        c = new Color(c1.getRed(), c1.getRed(), c1.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m2){
                        c = new Color(c2.getRed(), c2.getRed(), c2.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m3){
                        c = new Color(c3.getRed(), c3.getRed(), c3.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m4){
                        c = new Color(c4.getRed(), c4.getRed(), c4.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == 5){
                        c = new Color(c5.getRed(), c5.getRed(), c5.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                }
                else if(j == 0 && i == entrada.getHeight() - 1){                            // CASO 6
                    
                    int m0 = 0;
                    int m1 = 0;
                    int m2 = 0;
                    int m3 = 0;
                    Color c0 = new Color(entrada.getRGB(j, i));
                    Color c1 = new Color(entrada.getRGB(j, i - 1));
                    Color c2 = new Color(entrada.getRGB(j + 1, i - 1));
                    Color c3 = new Color(entrada.getRGB(j + 1, i));
                    
                    int[] vet = new int[4];
                    
                    if(c0.getRed() == c1.getRed()){
                        m0 ++;
                        m1 ++;
                    }
                    if(c0.getRed() == c2.getRed()){
                        m0 ++;
                        m2 ++;
                    }
                    if(c0.getRed() == c3.getRed()){
                        m0 ++;
                        m3 ++;
                    }
                    if(c1.getRed() == c2.getRed()){
                        m1 ++;
                        m2 ++;
                    }
                    if(c1.getRed() == c3.getRed()){
                        m1 ++;
                        m3 ++;
                    }
                    if(c2.getRed() == c3.getRed()){
                        m2 ++;
                        m3 ++;
                    }
                    
                    vet[0] = m0; vet[1] = m1; vet[2] = m2; vet[3] = m3;
                    Arrays.sort(vet);
                    
                    if(vet[3] == m0){
                        c = new Color(c0.getRed(), c0.getRed(), c0.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[3] == m1){
                        c = new Color(c1.getRed(), c1.getRed(), c1.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[3] == m2){
                        c = new Color(c2.getRed(), c2.getRed(), c2.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[3] == m3){
                        c = new Color(c3.getRed(), c3.getRed(), c3.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                }
                else if(i == entrada.getHeight() - 1 && j > 0 && j < entrada.getWidth() - 1){                           // CASO 7
                    
                    int m0 = 0;
                    int m1 = 0;
                    int m2 = 0;
                    int m3 = 0;
                    int m4 = 0;
                    int m5 = 0;
                    Color c0 = new Color(entrada.getRGB(j, i));
                    Color c1 = new Color(entrada.getRGB(j, i - 1));
                    Color c2 = new Color(entrada.getRGB(j + 1, i - 1));
                    Color c3 = new Color(entrada.getRGB(j + 1, i));
                    Color c4 = new Color(entrada.getRGB(j - 1, i));
                    Color c5 = new Color(entrada.getRGB(j - 1, i - 1));
                    
                    int[] vet = new int[6];
                    
                    if(c0.getRed() == c1.getRed()){
                        m0 ++;
                        m1 ++;
                    }
                    if(c0.getRed() == c2.getRed()){
                        m0 ++;
                        m2 ++;
                    }
                    if(c0.getRed() == c3.getRed()){
                        m0 ++;
                        m3 ++;
                    }
                    if(c0.getRed() == c4.getRed()){
                        m0 ++;
                        m4 ++;
                    }
                    if(c0.getRed() == c5.getRed()){
                        m0 ++;
                        m5 ++;
                    }
                    if(c1.getRed() == c2.getRed()){
                        m1 ++;
                        m2 ++;
                    }
                    if(c1.getRed() == c3.getRed()){
                        m1 ++;
                        m3 ++;
                    }
                    if(c1.getRed() == c4.getRed()){
                        m1 ++;
                        m4 ++;
                    }
                    if(c1.getRed() == c5.getRed()){
                        m1 ++;
                        m5 ++;
                    }
                    if(c2.getRed() == c3.getRed()){
                        m2 ++;
                        m3 ++;
                    }
                    if(c2.getRed() == c4.getRed()){
                        m2 ++;
                        m4 ++;
                    }
                    if(c2.getRed() == c5.getRed()){
                        m2 ++;
                        m5 ++;
                    }
                    if(c3.getRed() == c4.getRed()){
                        m3 ++;
                        m4 ++;
                    }
                    if(c3.getRed() == c5.getRed()){
                        m3 ++;
                        m5 ++;
                    }
                    if(c4.getRed() == c5.getRed()){
                        m4 ++;
                        m5 ++;
                    }
                    
                    vet[0] = m0; vet[1] = m1; vet[2] = m2;
                    vet[3] = m3; vet[4] = m4; vet[5] = m5;
                    Arrays.sort(vet);
                    
                    if(vet[5] == m0){
                        c = new Color(c0.getRed(), c0.getRed(), c0.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m1){
                        c = new Color(c1.getRed(), c1.getRed(), c1.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m2){
                        c = new Color(c2.getRed(), c2.getRed(), c2.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m3){
                        c = new Color(c3.getRed(), c3.getRed(), c3.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == m4){
                        c = new Color(c4.getRed(), c4.getRed(), c4.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[5] == 5){
                        c = new Color(c5.getRed(), c5.getRed(), c5.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                }
                else if(i == entrada.getHeight() - 1 && j == entrada.getWidth() - 1){                           // CASO 8
                    
                    int m0 = 0;
                    int m1 = 0;
                    int m2 = 0;
                    int m3 = 0;
                    Color c0 = new Color(entrada.getRGB(j, i));
                    Color c1 = new Color(entrada.getRGB(j, i - 1));
                    Color c2 = new Color(entrada.getRGB(j - 1, i));
                    Color c3 = new Color(entrada.getRGB(j - 1, i - 1));
                    
                    int[] vet = new int[4];
                    
                    if(c0.getRed() == c1.getRed()){
                        m0 ++;
                        m1 ++;
                    }
                    if(c0.getRed() == c2.getRed()){
                        m0 ++;
                        m2 ++;
                    }
                    if(c0.getRed() == c3.getRed()){
                        m0 ++;
                        m3 ++;
                    }
                    if(c1.getRed() == c2.getRed()){
                        m1 ++;
                        m2 ++;
                    }
                    if(c1.getRed() == c3.getRed()){
                        m1 ++;
                        m3 ++;
                    }
                    if(c2.getRed() == c3.getRed()){
                        m2 ++;
                        m3 ++;
                    }
                    
                    vet[0] = m0; vet[1] = m1; vet[2] = m2; vet[3] = m3;
                    Arrays.sort(vet);
                    
                    if(vet[3] == m0){
                        c = new Color(c0.getRed(), c0.getRed(), c0.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[3] == m1){
                        c = new Color(c1.getRed(), c1.getRed(), c1.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[3] == m2){
                        c = new Color(c2.getRed(), c2.getRed(), c2.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[3] == m3){
                        c = new Color(c3.getRed(), c3.getRed(), c3.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                }
                else{                           // CASO GERAL
                    int m0 = 0;
                    int m1 = 0;
                    int m2 = 0;
                    int m3 = 0;
                    int m4 = 0;
                    int m5 = 0;
                    int m6 = 0;
                    int m7 = 0;
                    int m8 = 0;
                    Color c0 = new Color(entrada.getRGB(j, i));
                    Color c1 = new Color(entrada.getRGB(j, i - 1));
                    Color c2 = new Color(entrada.getRGB(j + 1, i - 1));
                    Color c3 = new Color(entrada.getRGB(j + 1, i));
                    Color c4 = new Color(entrada.getRGB(j + 1, i + 1));
                    Color c5 = new Color(entrada.getRGB(j, i + 1));
                    Color c6 = new Color(entrada.getRGB(j - 1, i + 1));
                    Color c7 = new Color(entrada.getRGB(j - 1, i));
                    Color c8 = new Color(entrada.getRGB(j - 1, i - 1));
                    
                    int[] vet = new int[9];
                    
                    if(c0.getRed() == c1.getRed()){
                        m0 ++;
                        m1 ++;
                    }
                    if(c0.getRed() == c2.getRed()){
                        m0 ++;
                        m2 ++;
                    }
                    if(c0.getRed() == c3.getRed()){
                        m0 ++;
                        m3 ++;
                    }
                    if(c0.getRed() == c4.getRed()){
                        m0 ++;
                        m4 ++;
                    }
                    if(c0.getRed() == c5.getRed()){
                        m0 ++;
                        m5 ++;
                    }
                    if(c0.getRed() == c6.getRed()){
                        m0 ++;
                        m6 ++;
                    }
                    if(c0.getRed() == c7.getRed()){
                        m0 ++;
                        m7 ++;
                    }
                    if(c0.getRed() == c8.getRed()){
                        m0 ++;
                        m8 ++;
                    }
                    if(c1.getRed() == c2.getRed()){
                        m1 ++;
                        m2 ++;
                    }
                    if(c1.getRed() == c3.getRed()){
                        m1 ++;
                        m3 ++;
                    }
                    if(c1.getRed() == c4.getRed()){
                        m1 ++;
                        m4 ++;
                    }
                    if(c1.getRed() == c5.getRed()){
                        m1 ++;
                        m5 ++;
                    }
                    if(c1.getRed() == c6.getRed()){
                        m1 ++;
                        m6 ++;
                    }
                    if(c1.getRed() == c7.getRed()){
                        m1 ++;
                        m7 ++;
                    }
                    if(c1.getRed() == c8.getRed()){
                        m1 ++;
                        m8 ++;
                    }
                    if(c2.getRed() == c3.getRed()){
                        m2 ++;
                        m3 ++;
                    }
                    if(c2.getRed() == c4.getRed()){
                        m2 ++;
                        m4 ++;
                    }
                    if(c2.getRed() == c5.getRed()){
                        m2 ++;
                        m5 ++;
                    }
                    if(c2.getRed() == c6.getRed()){
                        m2 ++;
                        m6 ++;
                    }
                    if(c2.getRed() == c7.getRed()){
                        m2 ++;
                        m7 ++;
                    }
                    if(c2.getRed() == c8.getRed()){
                        m2 ++;
                        m8 ++;
                    }
                    if(c3.getRed() == c4.getRed()){
                        m3 ++;
                        m4 ++;
                    }
                    if(c3.getRed() == c5.getRed()){
                        m3 ++;
                        m5 ++;
                    }
                    if(c3.getRed() == c6.getRed()){
                        m3 ++;
                        m6 ++;
                    }
                    if(c3.getRed() == c7.getRed()){
                        m3 ++;
                        m7 ++;
                    }
                    if(c3.getRed() == c8.getRed()){
                        m3 ++;
                        m8 ++;
                    }
                    if(c4.getRed() == c5.getRed()){
                        m4 ++;
                        m5 ++;
                    }
                    if(c4.getRed() == c6.getRed()){
                        m4 ++;
                        m6 ++;
                    }
                    if(c4.getRed() == c7.getRed()){
                        m4 ++;
                        m7 ++;
                    }
                    if(c4.getRed() == c8.getRed()){
                        m4 ++;
                        m8 ++;
                    }
                    if(c5.getRed() == c6.getRed()){
                        m5 ++;
                        m6 ++;
                    }
                    if(c5.getRed() == c7.getRed()){
                        m5 ++;
                        m7 ++;
                    }
                    if(c5.getRed() == c8.getRed()){
                        m5 ++;
                        m8 ++;
                    }
                    if(c6.getRed() == c7.getRed()){
                        m6 ++;
                        m7 ++;
                    }
                    if(c6.getRed() == c8.getRed()){
                        m6 ++;
                        m8 ++;
                    }
                    if(c7.getRed() == c8.getRed()){
                        m7 ++;
                        m8 ++;
                    }
                    
                    vet[0] = m0; vet[1] = m1; vet[2] = m2;
                    vet[3] = m3; vet[4] = m4; vet[5] = m5;
                    vet[6] = m6; vet[7] = m7; vet[8] = m8;
                    Arrays.sort(vet);
                    
                    if(vet[8] == m0){
                        c = new Color(c0.getRed(), c0.getRed(), c0.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[8] == m1){
                        c = new Color(c1.getRed(), c1.getRed(), c1.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[8] == m2){
                        c = new Color(c2.getRed(), c2.getRed(), c2.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[8] == m3){
                        c = new Color(c3.getRed(), c3.getRed(), c3.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[8] == m4){
                        c = new Color(c4.getRed(), c4.getRed(), c4.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[8] == m5){
                        c = new Color(c5.getRed(), c5.getRed(), c5.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[8] == m6){
                        c = new Color(c6.getRed(), c6.getRed(), c6.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[8] == m7){
                        c = new Color(c7.getRed(), c7.getRed(), c7.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                    else if(vet[8] == m8){
                        c = new Color(c8.getRed(), c8.getRed(), c8.getRed());
                        img1.setRGB(j, i, c.getRGB());
                    }
                }
            }
        }
    }
        return img1;
    }
    
    public static void setLabel(JLabel label){
        lbl = label;
    }
    
    static double[][] matImportancia;                                           //MATRIZ DE IMPORTÂNCIAS.
    static boolean[][] matRemoved1;                                             //MATRIZ PARA MAPEAR PIXELS REMOVIDOS NA AMOSTRA 1.
    static boolean[][] matRemoved2;                                             //MATRIZ PARA MAPEAR PIXELS REMOVIDOS NA AMOSTRA 2.
    static boolean[][] aux;                                                     //MATRIZ AUXILIAR PARA MANTER COPIA DE matRemoved1 E matRemoved2.
    public static JLabel lbl;
}
