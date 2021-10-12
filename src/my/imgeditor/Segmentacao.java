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
    
    public static BufferedImage SDGM(BufferedImage entrada){                                    //CHAMADA AO METODO S-DGM() COM UMA IMAGEM DE ENTRADA.
        
        /**
         * O metodo S-DGM() realiza a segmentacao da imagem de entrada utilizando difusao geometrica Markoviana (DGM).
         * A DGM atribui importancias aos pixels da imagem, o que permite que o S-DGM elimine pixels pouco relevantes
         * para as formas dos objetos presentes na imagem.
         */                
        BufferedImage img1 = getAmostra1(0.59);                                                 //PRIMEIRA AMOSTRA DE IMAGEM.
        BufferedImage img2 = getAmostra2(0.66);                                                 //SEGUNDA AMOSTRA DE IMAGEM.
        img1 = removerRuido(img1, img2);                                                        //REMOÇÃO DE RUIDOS NA IMAGEM PROCESSADA.
                
        return img1;                                                                            //IMAGEM FINAL. 
    }
    
    public static BufferedImage removerRuido(BufferedImage entrada1, BufferedImage entrada2){
        
        /**
         *O metodo removeNoise() minimiza ruidos na imagem final, realizando uma combinacao entre duas amostras de imagem
         * geradas pelas duas etapas iniciais do metodo S-DGM().
         * Os ruidos são reduzidos, mas não eliminados totalmente da imagem.
         */
        
        BufferedImage img1 = new BufferedImage(entrada1.getWidth(), entrada1.getHeight(), BufferedImage.TYPE_INT_RGB);
        BufferedImage img2 = new BufferedImage(entrada2.getWidth(), entrada2.getHeight(), BufferedImage.TYPE_INT_RGB);
        matrizAuxiliar = new boolean[entrada1.getHeight()][entrada1.getWidth()];          //MATRIZ AUXILIAR PARA MANTER COPIA DE pixelsRemovidosAmostra1[][].
        
        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0; j < img1.getWidth(); j ++){
                
                matrizAuxiliar[i][j] = pixelsRemovidosAmostra1[i][j];                                 //COPIA DE pixelsRemovidosAmostra1[][] EM matrizAuxiliar[][].
                
                if(pixelsRemovidosAmostra1[i][j] == true){                                 //SOMENTE SÃO COPIADOS OS PIXELS QUE NAO FORAM ELIMINADOS NA CHAMADA DE getAmostra1().
                    Color c = new Color(entrada1.getRGB(j, i));
                    img1.setRGB(j, i, c.getRGB());
                }
                
                if(pixelsRemovidosAmostra2[i][j] == true){                                 //SOMENTE SÃO COPIADOS OS PIXELS QUE NAO FORAM ELIMINADOS NA CHAMADA DE getAmostra2().
                    Color c = new Color(entrada2.getRGB(j, i));
                    img2.setRGB(j, i, c.getRGB());
                }
            }
        }
        
        for(int i = 0; i < img2.getHeight(); i ++){
            for(int j = 0; j < img2.getWidth(); j ++){
                if(pixelsRemovidosAmostra2[i][j] == true){                                 
                    matrizAuxiliar[i][j] = false;                                         //MARCA COMO false EM matrizAuxiliar[][] OS PIXELS QUE "SOBRAM" EM img2 E FALTAM EM img1.
                }                                                              //COMO img2 POSSUI MENOS PIXELS ATIVOS QUE img1, NÃO EXISTE RISCO DE ELIMINAÇÃO INDEVIDA.
            }
        }
        
        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0; j < img1.getWidth(); j ++){
                if(matrizAuxiliar[i][j] == true){
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
    
    private static BufferedImage getAmostra1(double valor){
        
        BufferedImage img1 = RGB.TomDeCinzaSDGM();                                              //IMAGEM É TRANSFORMADA EM ESCALA DE CINZA.
        pixelsRemovidosAmostra1 = new boolean[img1.getHeight()][img1.getWidth()];               //INICIA-SE A MATRIZ BOOLEANA PARA MAPEAR QUEM SERÁ REMOVIDO DA IMAGEM.
        
        for(int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i ++){
            for(int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j ++){
                pixelsRemovidosAmostra1[i][j] = true;
            }
        }
        
        setImportanciasAmostra1(img1);                                                          //CALCULA-SE E ATRIBUI-SE AS IMPORTÂNCIAS DOS PIXELS.
        int tam = 0;
        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0; j < img1.getWidth(); j ++){
                tam ++;                                                         
            }                    
        }       

        double[] vet = new double[tam];                                                       //CRIA-SE UM VETOR DO TAMANHO DA QUANTIDADE DE PIXELS DA IMAGEM.
        tam = 0;                                                                
        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0; j < img1.getWidth(); j ++){
                if(pixelsRemovidosAmostra1[i][j] == true){
                    vet[tam] = matrizDeImportancias[i][j];                            //COPIA-SE AS IMPORTÂNCIAS DA MATRIZ PARA O VETOR.
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
                    if(pixelsRemovidosAmostra1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra1[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            pixelsRemovidosAmostra1[i][j] = false;
                        }
                        else{                            
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra1[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == 0 && j > 0 && j < img1.getWidth() - 1){            // CASO 2
                    if(pixelsRemovidosAmostra1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra1[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            pixelsRemovidosAmostra1[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra1[i][j] = false;
                            }
                        }
                    }                        
                }

                else if(i == 0 && j == img1.getWidth() - 1){                    // CASO 3
                    if(pixelsRemovidosAmostra1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra1[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            pixelsRemovidosAmostra1[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra1[i][j] = false;
                            }
                        }
                    }
                }

                else if(j == 0 && i > 0 && i < img1.getHeight() - 1){           // CASO 4
                    if(pixelsRemovidosAmostra1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra1[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            pixelsRemovidosAmostra1[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra1[i][j] = false;
                            }
                        }
                    }                                              
                }

                else if(j == img1.getWidth() - 1 && i > 0 && i < img1.getHeight() - 1){     // CASO 5
                    if(pixelsRemovidosAmostra1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra1[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            pixelsRemovidosAmostra1[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra1[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == img1.getHeight() - 1 && j == 0){                   // CASO 6
                    if(pixelsRemovidosAmostra1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra1[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            pixelsRemovidosAmostra1[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra1[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == img1.getHeight() - 1 && j > 0 && j < img1.getWidth() - 1){     // CASO 7
                    if(pixelsRemovidosAmostra1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra1[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            pixelsRemovidosAmostra1[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra1[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == img1.getHeight() - 1 && j == img1.getWidth() - 1){     // CASO 8
                    if(pixelsRemovidosAmostra1[i][j] == true){
                        int qtdVizinhos = 0;
                        
                        if(pixelsRemovidosAmostra1[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            pixelsRemovidosAmostra1[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra1[i][j] = false;
                            }
                        }
                    }
                }

                else{                                                           // CASO GERAL
                    if(pixelsRemovidosAmostra1[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra1[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra1[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }
                        
                        if(qtdVizinhos < 3){
                            pixelsRemovidosAmostra1[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra1[i][j] = false;
                            }
                        }
                    }
                }                                          
            }                    
        }

        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0 ; j < img1.getWidth(); j ++){
                if(pixelsRemovidosAmostra1[i][j] == false){
                    Color c = new Color(0, 0, 0);
                    img1.setRGB(j, i, c.getRGB());                              //ELIMINA-SE OS PIXELS MARCADOS PARA REMOÇÃO.                 
                }
            }
        }        
        return img1;
    }
    
    private static BufferedImage getAmostra2(double valor){
        
        BufferedImage img1 = RGB.TomDeCinzaSDGM();
        pixelsRemovidosAmostra2 = new boolean[img1.getHeight()][img1.getWidth()];
        
        for(int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i ++){
            for(int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j ++){
                pixelsRemovidosAmostra2[i][j] = true;
            }
        }
        
        setImportanciasAmostra2(img1);
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
                if(pixelsRemovidosAmostra2[a][b] == true){
                    vet[tam] = matrizDeImportancias[a][b];
                    tam ++;
                }
            }
        }

        Arrays.sort(vet);
        int percentil = (int)(tam*valor);

        for(int i = 0; i < img1.getHeight(); i ++){
            for(int j = 0; j < img1.getWidth(); j ++){                   

                if(i == 0 && j == 0){                        // CASO 1                        
                    if(pixelsRemovidosAmostra2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra2[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            pixelsRemovidosAmostra2[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra2[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == 0 && j > 0 && j < img1.getWidth() - 1){                         // CASO 2
                    if(pixelsRemovidosAmostra2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra2[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            pixelsRemovidosAmostra2[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra2[i][j] = false;
                            }
                        }
                    }                        
                }

                else if(i == 0 && j == img1.getWidth() - 1){                         // CASO 3
                    if(pixelsRemovidosAmostra2[i][j] == true){
                        int qtdVizinhos = 0;
                        
                        if(pixelsRemovidosAmostra2[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            pixelsRemovidosAmostra2[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra2[i][j] = false;
                            }
                        }
                    }
                }

                else if(j == 0 && i > 0 && i < img1.getHeight() - 1){                            // CASO 4
                    if(pixelsRemovidosAmostra2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra2[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            pixelsRemovidosAmostra2[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra2[i][j] = false;
                            }
                        }
                    }                                              
                }

                else if(j == img1.getWidth() - 1 && i > 0 && i < img1.getHeight() - 1){                           // CASO 5
                    if(pixelsRemovidosAmostra2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra2[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            pixelsRemovidosAmostra2[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra2[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == img1.getHeight() - 1 && j == 0){                            // CASO 6
                    if(pixelsRemovidosAmostra2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra2[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            pixelsRemovidosAmostra2[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra2[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == img1.getHeight() - 1 && j > 0 && j < img1.getWidth() - 1){                           // CASO 7
                    if(pixelsRemovidosAmostra2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra2[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i - 1][j + 1] == true){
                        }

                        if(pixelsRemovidosAmostra2[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos < 3){
                            pixelsRemovidosAmostra2[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra2[i][j] = false;
                            }
                        }
                    }
                }

                else if(i == img1.getHeight() - 1 && j == img1.getWidth() - 1){                           // CASO 8
                    if(pixelsRemovidosAmostra2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra2[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(qtdVizinhos == 0){
                            pixelsRemovidosAmostra2[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra2[i][j] = false;
                            }
                        }
                    }
                }

                else{                           // CASO GERAL
                    if(pixelsRemovidosAmostra2[i][j] == true){
                        int qtdVizinhos = 0;

                        if(pixelsRemovidosAmostra2[i - 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i - 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j + 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i + 1][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i][j - 1] == true){
                            qtdVizinhos ++;
                        }

                        if(pixelsRemovidosAmostra2[i - 1][j - 1] == true){
                            qtdVizinhos ++;
                        }                        
                        if(qtdVizinhos < 3){
                            pixelsRemovidosAmostra2[i][j] = false;
                        }
                        else{
                            if(matrizDeImportancias[i][j] < vet[percentil]){
                                pixelsRemovidosAmostra2[i][j] = false;
                            }
                        }
                    }
                }                                          
            }                    
        }

        for(int a = 0; a < img1.getHeight(); a ++){
            for(int b = 0 ; b < img1.getWidth(); b ++){
                if(pixelsRemovidosAmostra2[a][b] == false){
                    Color c = new Color(0, 0, 0);
                    img1.setRGB(b, a, c.getRGB());
                }
            }
        }            
        
        return img1;
    }
    
    private static void setImportanciasAmostra1(BufferedImage entrada){        
        int qtdVizinhos = 0;        
        matrizDeImportancias = new double[entrada.getHeight()][entrada.getWidth()];       //MATRIZ DE double PARA ARMAZENAR AS IMPROTÂNCIAS DE CADA PIXEL.
        
        for(int i = 0; i < entrada.getHeight(); i ++){
            for(int j = 0; j < entrada.getWidth(); j ++){                
                double soma = 0.0;                
                if(i == 0 && j == 0){                           // CASO 1                    
                    if(pixelsRemovidosAmostra1[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra1[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));          //NUCLEO DA DGM E' CALCULADO
                            qtdVizinhos ++;                                                         //EM FUNÇÃO DA DISTÂNCIA ENTRE AS CORES DOS PIXELS.
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));                           //IMPORTÂNCIA E' NORMALIZADA E
                        matrizDeImportancias[i][j] = importancia;                                        //ARMAZENADA NA MATRIZ.
                        qtdVizinhos = 0;
                    }
                }
                else if(i == 0 && j > 0 && j < entrada.getWidth() - 1){                         // CASO 2                    
                    if(pixelsRemovidosAmostra1[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra1[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == 0 && j == entrada.getWidth() - 1){                         // CASO 3                    
                    if(pixelsRemovidosAmostra1[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra1[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(j == 0 && i > 0 && i < entrada.getHeight() - 1){                            // CASO 4                    
                    if(pixelsRemovidosAmostra1[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra1[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(j == entrada.getWidth() - 1 && i > 0 && i < entrada.getHeight() - 1){                           // CASO 5
                    if(pixelsRemovidosAmostra1[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra1[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == entrada.getHeight() - 1 && j == 0){                            // CASO 6
                    if(pixelsRemovidosAmostra1[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra1[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == entrada.getHeight() - 1 && j > 0 && j < entrada.getWidth() - 1){                           // CASO 7
                    if(pixelsRemovidosAmostra1[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra1[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == entrada.getHeight() - 1 && j == entrada.getWidth() - 1){                           // CASO 8
                    if(pixelsRemovidosAmostra1[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra1[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else{                           // CASO GERAL
                    if(pixelsRemovidosAmostra1[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra1[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra1[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
            }
        }
    }
    
    private static void setImportanciasAmostra2(BufferedImage entrada){
        
        int qtdVizinhos = 0;
        matrizDeImportancias = new double[entrada.getHeight()][entrada.getWidth()];
        
        for(int i = 0; i < entrada.getHeight(); i ++){
            for(int j = 0; j < entrada.getWidth(); j ++){                
                double soma = 0.0;                
                if(i == 0 && j == 0){                           // CASO 1                    
                    if(pixelsRemovidosAmostra2[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra2[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == 0 && j > 0 && j < entrada.getWidth() - 1){                         // CASO 2                    
                    if(pixelsRemovidosAmostra2[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra2[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == 0 && j == entrada.getWidth() - 1){                         // CASO 3                    
                    if(pixelsRemovidosAmostra2[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra2[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(j == 0 && i > 0 && i < entrada.getHeight() - 1){                            // CASO 4                    
                    if(pixelsRemovidosAmostra2[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra2[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(j == entrada.getWidth() - 1 && i > 0 && i < entrada.getHeight() - 1){                           // CASO 5
                    if(pixelsRemovidosAmostra2[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra2[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == entrada.getHeight() - 1 && j == 0){                            // CASO 6
                    if(pixelsRemovidosAmostra2[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra2[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == entrada.getHeight() - 1 && j > 0 && j < entrada.getWidth() - 1){                           // CASO 7
                    if(pixelsRemovidosAmostra2[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra2[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else if(i == entrada.getHeight() - 1 && j == entrada.getWidth() - 1){                           // CASO 8
                    if(pixelsRemovidosAmostra2[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra2[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
                else{                                                                                           // CASO GERAL
                    if(pixelsRemovidosAmostra2[i][j] == true){                        
                        Color c = new Color(entrada.getRGB(j, i));                        
                        if(pixelsRemovidosAmostra2[i - 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i - 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j + 1] == true){
                            Color c1 = new Color(entrada.getRGB(j + 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j] == true){
                            Color c1 = new Color(entrada.getRGB(j, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i + 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i + 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }
                        if(pixelsRemovidosAmostra2[i - 1][j - 1] == true){
                            Color c1 = new Color(entrada.getRGB(j - 1, i - 1));
                            soma += Math.exp(-(Math.abs(c.getRed() - c1.getRed())/255.0));
                            qtdVizinhos ++;
                        }                        
                        double importancia = (qtdVizinhos/(1.0 + soma));
                        matrizDeImportancias[i][j] = importancia;
                        qtdVizinhos = 0;
                    }
                }
            }
        }
    }
        
    public static void setLabel(JLabel label){
        labelImagem = label;
    }
    
    static double[][] matrizDeImportancias;                                                     //Matriz que armazena a importância calculada para cada pixel da imagem.
    static boolean[][] pixelsRemovidosAmostra1;                                                 //Matriz que mapeia pixels removidos na amostra de imagem 1.
    static boolean[][] pixelsRemovidosAmostra2;                                                 //Matriz que mapeia pixels removidos na amostra de imagem 2.
    static boolean[][] matrizAuxiliar;                                                          //Matriz auxiliar, utilizada para manter cópias de .
    public static JLabel labelImagem;
}
