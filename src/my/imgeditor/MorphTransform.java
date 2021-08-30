/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.imgeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Iverton Perboyre L. Maia
 */
public class MorphTransform {
   
    public static BufferedImage Rotacao(int x){        
        
        boolean[][] mat = new boolean[meuJFrame.imagem2.getHeight()][meuJFrame.imagem2.getWidth()];
        int centrolin = meuJFrame.imagem2.getHeight()/2;
        int centrocol = meuJFrame.imagem2.getWidth()/2;

        for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                mat[i][j] = false;
                Color c = new Color(0, 51, 102);
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }
        }
    
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                int novalin = (int) Math.round((i - centrolin) * Math.cos(Math.toRadians(x)) - (j - centrocol) * Math.sin(Math.toRadians(x)) + centrolin);
                int novacol = (int) Math.round((i - centrolin) * Math.sin(Math.toRadians(x)) + (j - centrocol) * Math.cos(Math.toRadians(x)) + centrocol);
                if (novalin >= 0 && novalin < meuJFrame.imagem2.getHeight() && novacol >= 0 && novacol < meuJFrame.imagem2.getWidth()) {
                    meuJFrame.imagem2.setRGB(novacol, novalin, c.getRGB());
                    mat[novalin][novacol] = true;
                }
            }
        }

        for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                Color c;
                if(mat[i][j] == false){                    
                    if((j == 0 && i <= meuJFrame.imagem2.getHeight() - 1)){
                        Color dir = new Color(meuJFrame.imagem2.getRGB(j + 1, i));
                        c = new Color(dir.getRed(), dir.getGreen(), dir.getBlue());
                        mat[i][j] = true;
                        meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                    }
                    else if(j == meuJFrame.imagem2.getWidth() - 1 && i <= meuJFrame.imagem2.getHeight() - 1){
                        Color esq = new Color(meuJFrame.imagem2.getRGB(j - 1, i));
                        c = new Color(esq.getRed(), esq.getGreen(), esq.getBlue());
                        mat[i][j] = true;
                        meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                    }
                    else{
                        Color esq = new Color(meuJFrame.imagem2.getRGB(j - 1, i));
                        Color dir = new Color(meuJFrame.imagem2.getRGB(j + 1, i));
                        int R = (esq.getRed() + dir.getRed()) / 2;
                        int G = (esq.getGreen() + dir.getGreen()) / 2;
                        int B = (esq.getBlue() + dir.getBlue()) / 2;
                        c = new Color(R, G, B);
                        mat[i][j] = true;
                        meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                    }
                }
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return img;
    }
    
    public static BufferedImage Rotacao90(){
        soma = soma + 90;
        int centrolin = meuJFrame.imagem2.getHeight()/2;
        int centrocol = meuJFrame.imagem2.getWidth()/2;
        boolean[][] mat = new boolean[meuJFrame.imagem2.getHeight()][meuJFrame.imagem2.getWidth()];

        for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                    mat[i][j] = false;
                    Color c = new Color(0, 51, 102);
                    meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }
        }

        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                int novalin = (int) Math.round(centrolin + (i - centrolin) * Math.cos(Math.toRadians(soma)) - (j - centrocol) * Math.sin(Math.toRadians(soma)));
                int novacol = (int) Math.round(centrocol + (i - centrolin) * Math.sin(Math.toRadians(soma)) + (j - centrocol) * Math.cos(Math.toRadians(soma)));
                if (novalin >= 0 && novalin < meuJFrame.imagem2.getHeight() && novacol >= 0 && novacol < meuJFrame.imagem2.getWidth()) {
                    meuJFrame.imagem2.setRGB(novacol, novalin, c.getRGB());
                    mat[novalin][novacol] = true;
                }
            }
        }
        
        for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                Color c;
                if(mat[i][j] == false){                    
                    if((j == 0 && i <= meuJFrame.imagem2.getHeight() - 1)){
                        Color dir = new Color(meuJFrame.imagem2.getRGB(j + 1, i));
                        c = new Color(dir.getRed(), dir.getGreen(), dir.getBlue());
                        mat[i][j] = true;
                        meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                    }
                    else if(j == meuJFrame.imagem2.getWidth() - 1 && i <= meuJFrame.imagem2.getHeight() - 1){
                        Color esq = new Color(meuJFrame.imagem2.getRGB(j - 1, i));
                        c = new Color(esq.getRed(), esq.getGreen(), esq.getBlue());
                        mat[i][j] = true;
                        meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                    }
                    else{
                        Color esq = new Color(meuJFrame.imagem2.getRGB(j - 1, i));
                        Color dir = new Color(meuJFrame.imagem2.getRGB(j + 1, i));
                        int R = (esq.getRed() + dir.getRed()) / 2;
                        int G = (esq.getGreen() + dir.getGreen()) / 2;
                        int B = (esq.getBlue() + dir.getBlue()) / 2;
                        c = new Color(R, G, B);
                        mat[i][j] = true;
                        meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                    }
                }
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return img;
    }
    
    public static BufferedImage EspelharVertical(){
        
        img = new BufferedImage(meuJFrame.imagem2.getWidth(), meuJFrame.imagem2.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for(int i = 0; i < img.getHeight(); i++){
            for(int j = 0, jimg = img.getWidth() - 1; j < img.getWidth(); j++, jimg--){
                Color c = new Color(meuJFrame.imagem2.getRGB(j, i));
                img.setRGB(jimg, i,c.getRGB());
            }
        }
        
        for(int i = 0; i < img.getHeight(); i++){
            for(int j = 0; j < img.getWidth(); j++){
                Color c = new Color(img.getRGB(j, i));
                meuJFrame.imagem2.setRGB(j, i,c.getRGB());
            }
        }
        
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return img;
    }
    
    public static BufferedImage EspelharHorizontal(){
        
        img = new BufferedImage(meuJFrame.imagem2.getWidth(), meuJFrame.imagem2.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for(int i = 0, iimg = img.getHeight()-1; i < img.getHeight(); i++, iimg--){
            for(int j = 0; j < img.getWidth(); j++){
                Color c = new Color(meuJFrame.imagem2.getRGB(j, i));
                img.setRGB(j, iimg,c.getRGB());
            }
        }
        
        for(int i = 0; i < img.getHeight(); i++){
            for(int j = 0; j < img.getWidth(); j++){
                Color c = new Color(img.getRGB(j, i));
                meuJFrame.imagem2.setRGB(j, i,c.getRGB());
            }
        }
        
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return img;
    }
    
    public static BufferedImage PinchVertical(BufferedImage entrada){ 
        
        /**
         *O metodo PinchVertical() "belisca" a imagem em suas linhas.
         * Para isto, é utilizada a técnica de contração de linhas na imagem original,
         * e é por isso que este efeito recebe o nome “vertical”.
         */
               
        double indpinch = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite um numero inteiro entre 0 e " + entrada.getHeight() + " para contrair a imagem "));
        while (indpinch < 1 || indpinch > entrada.getHeight()) {
            indpinch = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite um numero inteiro entre 0 e " + entrada.getHeight() + " para contrair a imagem "));
        }
        
        img = new BufferedImage(entrada.getWidth(), entrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        int centrolin = entrada.getHeight() / 2;
        int centrocol = entrada.getWidth() / 2;
        double fator = 0.0;
        boolean[][] mat = new boolean[entrada.getHeight()][entrada.getWidth()];

        for (int i = 0; i < entrada.getHeight(); i++) {
            for (int j = 0; j < entrada.getWidth(); j++) {
                mat[i][j] = false;
                Color c = new Color(0, 102, 102);
                img.setRGB(j, i, c.getRGB());
            }
        }

        for (int i = 0; i < entrada.getHeight(); i++) {
            if (i <= centrolin) {
                fator = (indpinch - 1) / centrolin * i + 1;
            } else if (i > centrolin) {
                fator = -1 * (indpinch - 1) / (entrada.getHeight() - 1 - centrolin) * (i - centrolin) + indpinch;
            }

            for (int j = 0; j < entrada.getWidth(); j++) {

                Color c = new Color(entrada.getRGB(j, i));
                int novacol = (int) Math.round((j - centrocol) / fator + centrocol);

                if (novacol >= 0 && novacol < entrada.getWidth()) {
                    img.setRGB(novacol, i, c.getRGB());
                }
            }
        }
            
        return img;
    }
    
    public static BufferedImage PinchVerticalDeBordas(BufferedImage entrada){
        
        /**
         *O PinchVerticalDeBordas() se assemelha ao Pinch Vertical, diferindo apenas no cálculo do fator de contração de cada linha.
         * O resultado obtido pela aplicação deste filtro é uma imagem com Pinch inverso ao exemplo anterior.
         * A linha central da imagem é mantida intacta e todas as outras sofrem o achatamento causado pela contração das linhas.  
         */
        
        double indpinch = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite um numero inteiro entre 0 e " + entrada.getHeight() + " para contrair a imagem "));
        while (indpinch < 0 || indpinch > entrada.getHeight()) {
            indpinch = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite um numero inteiro entre 0 e " + entrada.getHeight() + " para contrair a imagem "));
        }

        img = new BufferedImage(entrada.getWidth(), entrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        int centrolin = entrada.getHeight() / 2;
        int centrocol = entrada.getWidth() / 2;
        double fator = 0.0;        
        boolean[][] mat = new boolean[entrada.getHeight()][entrada.getWidth()];

        for (int i = 0; i < entrada.getHeight(); i++) {
            for (int j = 0; j < entrada.getWidth(); j++) {
                mat[i][j] = false;
                Color c = new Color(0, 102, 102);
                img.setRGB(j, i, c.getRGB());
            }
        }

        for (int i = 0; i < entrada.getHeight(); i++) {
            if (i <= centrolin) {
                fator = -(indpinch - 1) / centrolin * i + indpinch;
            } else if (i > centrolin) {
                fator = (indpinch - 1) / (entrada.getHeight() - 1 - centrolin) * (i - centrolin) + 1;
            }

            for (int j = 0; j < entrada.getWidth(); j++) {

                Color c = new Color(entrada.getRGB(j, i));
                int novacol = (int) Math.round((j - centrocol) / fator + centrocol);

                if (novacol >= 0 && novacol < entrada.getWidth()) {
                    img.setRGB(novacol, i, c.getRGB());
                }
            }
        }
        
        return img;
    }
    
    public static BufferedImage ZoomIn(BufferedImage entrada){
        
        /**
         *O metodo ZoomIn() implementada o conceito de interpolação bilinear.
         * A imagem resultante consiste em uma apliacao da imagem original de acordo com o fator fornecido pelo usuario.
         * O fator deve ser maior que 1.0 .
         */
        
        img = new BufferedImage(entrada.getWidth(), entrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        double fator = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite um fator maior que 1 para o Zoom In: "));
        while (fator <= 1) {
            fator = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite um fator maior que 1 para o Zoom In: "));
        }

        double passo = 1.0 / fator;
        double i;
        double j;
        int a;
        int b;

        for (i = 0.0, a = 0; a < entrada.getHeight(); a++) {
            for (j = 0.0, b = 0; b < entrada.getWidth(); b++) {

                Color pixel1 = new Color(entrada.getRGB((int) j, (int) i));
                Color pixel2 = new Color(entrada.getRGB((int) j, (int) (i + 1)));
                Color pixel3 = new Color(entrada.getRGB((int) (j + 1), (int) i));
                Color pixel4 = new Color(entrada.getRGB((int) (j + 1), (int) (i + 1)));

                double calc1;
                double calc2;
                double calc3;

                calc1 = pixel1.getRed() + (i - ((int) i)) * (pixel2.getRed() - pixel1.getRed());
                calc2 = pixel3.getRed() + (i - ((int) i)) * (pixel4.getRed() - pixel3.getRed());
                calc3 = calc1 + (j - ((int) j)) * (calc2 - calc1);
                if (calc3 > 255.0) {
                    calc3 = 255.0;
                }
                if (calc3 < 0.0) {
                    calc3 = 0.0;
                }
                int R = (int) Math.round(calc3);

                calc1 = pixel1.getGreen() + (i - ((int) i)) * (pixel2.getGreen() - pixel1.getGreen());
                calc2 = pixel3.getGreen() + (i - ((int) i)) * (pixel4.getGreen() - pixel3.getGreen());
                calc3 = calc1 + (j - ((int) j)) * (calc2 - calc1);
                if (calc3 > 255.0) {
                    calc3 = 255.0;
                }
                if (calc3 < 0.0) {
                    calc3 = 0.0;
                }
                int G = (int) Math.round(calc3);

                calc1 = pixel1.getBlue() + (i - ((int) i)) * (pixel2.getBlue() - pixel1.getBlue());
                calc2 = pixel3.getBlue() + (i - ((int) i)) * (pixel4.getBlue() - pixel2.getBlue());
                calc3 = calc1 + (j - ((int) j)) * (calc2 - calc1);
                if (calc3 > 255.0) {
                    calc3 = 255.0;
                }
                if (calc3 < 0.0) {
                    calc3 = 0.0;
                }
                int B = (int) Math.round(calc3);

                Color c = new Color(R, G, B);
                img.setRGB(b, a, c.getRGB());

                j = j + passo;
            }
            i = i + passo;
        }
        
        return img;
    }
    
    public static BufferedImage ZoomOut(BufferedImage entrada){
        
        /**
         *A operação de Zoom Out é implementada seguindo basicamente o mesmo raciocínio que a operação de Zoom In,
         * diferindo apenas nos valores permitidos como entrada.
         * No Zoom In, o usuário pode entrar com quaisquer valores maiores que 1.0.
         * No Zoom Out, o fator de redução da imagem deve estar obrigatoriamente entre 0 e 1.0.  
         */
        img = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB);
        double passo = 1.7;
        StructRGB[][] mat = new StructRGB[700][700];
        double i;
        double j;
        int a;
        int b;

        for (a = 0; a < 700; a++) {
            for (b = 0; b < 700; b++) {
                mat[a][b] = new StructRGB();
            }
        }

        for (i = 0.0, a = 0; a < 700; a++) {
            for (j = 0.0, b = 0; b < 700; b++) {

                Color pixel1 = new Color(entrada.getRGB((int) j, (int) i));
                Color pixel2 = new Color(entrada.getRGB((int) j, (int) (i + 1)));
                Color pixel3 = new Color(entrada.getRGB((int) (j + 1), (int) i));
                Color pixel4 = new Color(entrada.getRGB((int) (j + 1), (int) (i + 1)));

                double calc1;
                double calc2;
                double calc3;

                calc1 = pixel1.getRed() + (i - ((int) i)) * (pixel2.getRed() - pixel1.getRed());
                calc2 = pixel3.getRed() + (i - ((int) i)) * (pixel4.getRed() - pixel3.getRed());
                calc3 = calc1 + (j - ((int) j)) * (calc2 - calc1);
                if (calc3 > 255.0) {
                    calc3 = 255.0;
                }
                if (calc3 < 0.0) {
                    calc3 = 0.0;
                }
                int R = (int) Math.round(calc3);

                calc1 = pixel1.getGreen() + (i - ((int) i)) * (pixel2.getGreen() - pixel1.getGreen());
                calc2 = pixel3.getGreen() + (i - ((int) i)) * (pixel4.getGreen() - pixel3.getGreen());
                calc3 = calc1 + (j - ((int) j)) * (calc2 - calc1);
                if (calc3 > 255.0) {
                    calc3 = 255.0;
                }
                if (calc3 < 0.0) {
                    calc3 = 0.0;
                }
                int G = (int) Math.round(calc3);

                calc1 = pixel1.getBlue() + (i - ((int) i)) * (pixel2.getBlue() - pixel1.getBlue());
                calc2 = pixel3.getBlue() + (i - ((int) i)) * (pixel4.getBlue() - pixel2.getBlue());
                calc3 = calc1 + (j - ((int) j)) * (calc2 - calc1);
                if (calc3 > 255.0) {
                    calc3 = 255.0;
                }
                if (calc3 < 0.0) {
                    calc3 = 0.0;
                }
                int B = (int) Math.round(calc3);

                mat[a][b].r = R;
                mat[a][b].g = G;
                mat[a][b].b = B;

                j = j + passo;
            }
            i = i + passo;
        }

        for (a = 0; a < 700; a++) {
            for (b = 0; b < 700; b++) {
                if (a < 700 && b < 700) {
                    Color c;
                    c = new Color((int) mat[a][b].r, (int) mat[a][b].g, (int) mat[a][b].b);
                    img.setRGB(b, a, c.getRGB());
                } else {
                    Color c = new Color(0, 102, 102);
                    img.setRGB(b, a, c.getRGB());
                }
            }
        }
        
        return img;
    }
    
    public static void ApplyMorph(){
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imagem2.getRGB(j, i));
                int R = c.getRed();
                int G = c.getGreen();
                int B = c.getBlue();
                c = new Color(R, G, B);
                meuJFrame.imgcpy.setRGB(j, i, c.getRGB());
                meuJFrame.imgrevert.setRGB(j, i, c.getRGB());
            }
        }
        YIQ.RGBtoYIQ();
        HSL.RGBtoHSL();
        soma = 0;
    }
    
    public static void reverterMorph(){        
        for (int j = 0; j < meuJFrame.imgrevert.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imgrevert.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgrevert.getRGB(j, i));
                int R = c.getRed();
                int G = c.getGreen();
                int B = c.getBlue();                               
                c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                meuJFrame.imgcpy.setRGB(j, i, c.getRGB());
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = false;
        soma = 0;
    }
    
    public static void cancelarCheck(){
        for (int j = 0; j < meuJFrame.imgrevert.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imgrevert.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgrevert.getRGB(j, i));
                int R = c.getRed();
                int G = c.getGreen();
                int B = c.getBlue();                               
                c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                meuJFrame.imgcpy.setRGB(j, i, c.getRGB());
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        soma = 0;
    }
    
    public static void setLabel(JLabel label){
        lbl = label;
    }
    
    public static BufferedImage img;
    public static JLabel lbl;
    public static boolean applied = false;
    public static int morphid = 5;
    public static int soma = 0;
    
    static class StructRGB {

        double r;
        double g;
        double b;
        double peso;
    }
}
