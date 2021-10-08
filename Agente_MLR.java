/*
@author Luis Isael Campos Luna 
Instituto tecnologico José Mario Molina Pasquel Henríquez 
5 semestre, grupo B, T/M, 07/Oct/2021
 */
package AgenteMLR;
import jade.core.Agent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Agente_MLR extends Agent {
    double n;          //número de datos
    ArrayList<Double> Xi1=new ArrayList<>();//Determinante del sistema Xi1
    ArrayList<Double> Xi2=new ArrayList<>();//Determinante del sistema Xi2
    ArrayList<Double> Yi=new ArrayList<>();//Determinante del sistema Yi
    
    public void setup(){
        System.out.println("Para finalizar introduce 0 en ambas ");
        do {
            n= Integer.parseInt(JOptionPane.showInputDialog (null,"Introduce X1: ",
        "Agente X1",JOptionPane.QUESTION_MESSAGE));
            if (n != 0){
                Xi1.add(n);
            }
            n= Integer.parseInt(JOptionPane.showInputDialog (null,"Introduce X2: ",
        "Agente X2",JOptionPane.QUESTION_MESSAGE));
            if (n != 0){
                Xi2.add(n);
            }
            n= Integer.parseInt(JOptionPane.showInputDialog (null,"Introduce Y: ",
        "Agente Y",JOptionPane.QUESTION_MESSAGE));
            if (n != 0){
                Yi.add(n);
            }
        }while (n != 0);
        
        System.out.println("Ha introducido en X1: " + Xi1.size() + " números:");
        System.out.println("Ha introducido en X2: " + Xi2.size() + " números:");
        System.out.println("Ha introducido en Y:  " + Yi.size() + " números:");
       
        JOptionPane.showMessageDialog(null,  "Xi1:\n"+Xi1
        +"\nXi2: \n"+Xi2+"\nYi: \n"+Yi ,"Datos", JOptionPane.INFORMATION_MESSAGE);
        
    }
    public void Resultados(){
        System.out.println("Xi1 "+sumaXi1());
        System.out.println("Xi2 "+sumaXi2());
        System.out.println("Yi "+sumaYi());
        System.out.println("X2i1 "+sumX2i1());
        System.out.println("X2i2 "+sumX2i2());
        System.out.println("Xi1*Xi2 "+sumXi1Xi2());
        System.out.println("Xi2*Yi "+sumXi2Yi());
        System.out.println("Xi1*Yi "+sumXi1Yi());
        
        JOptionPane.showMessageDialog(null,"Sistema determinante: "+SystemDeterminant()
        +"\n Beta 0: "+Beta0()+"\n Beta 1: \n"+Beta1()+"\n Beta 2: "+Beta2() ,"Resultados basicos", JOptionPane.INFORMATION_MESSAGE);        
        System.out.println("+DS "+SystemDeterminant());
        Predict_Y();
    }
    public void Predict_Y(){
        int Num=1;
        double R;
        for(int i=0;i<Xi1.size();i++){
            R=Beta0()+(Beta1()*Xi1.get(i))+(Beta2()*Xi2.get(i));
           System.out.println("Yi "+Num+"= "+R);
           Num++;  
        }
    }
    public Agente_MLR() {
        setup();
    }
    public double Beta0(){//Con respecto a Beta0
        double R;
        double n;
        double d;
        n=((sumaYi()*sumX2i1()*sumX2i2())+(sumaXi1()*sumXi1Xi2()
        *sumXi2Yi())+(sumaXi2()*sumXi1Yi()*sumXi1Xi2()))-((sumXi2Yi()*sumX2i1()
        *sumaXi2())+(sumXi1Xi2()*sumXi1Xi2()*sumaYi())+(sumX2i2()*sumXi1Yi()
        *sumaXi1()));
        d=SystemDeterminant();
        R=n/d;
        return R;
    }

    public double Beta1(){//Con respecto a Beta1
        double R;
        double n;
        double d;
        n=((Xi1.size()*sumXi1Yi()*sumX2i2())+(sumaYi()*sumXi1Xi2()
        *sumaXi2())+(sumaXi2()*sumaXi1()*sumXi2Yi()))-((sumaXi2()*sumXi1Yi()
        *sumaXi2())+(sumXi2Yi()*sumXi1Xi2()*Xi1.size())+(sumX2i2()*sumaXi1()
        *sumaYi()));
        d=SystemDeterminant();
        R=n/d;
        return R;
    }
    public double Beta2(){//Con respecto a Beta2
        double R;
        double n;
        double d;
        n=((Xi1.size()*sumX2i1()*sumXi2Yi())+(sumaXi1()*sumXi1Yi()
        *sumaXi2())+(sumaYi()*sumaXi1()*sumXi1Xi2()))-((sumaXi2()*sumX2i1()
        *sumaYi())+(sumXi1Xi2()*sumXi1Yi()*Xi1.size())+(sumXi2Yi()*sumaXi1()
        *sumaXi1()));
        d=SystemDeterminant();
        R=n/d;
        return R;
    }
    //determinante del sistema
    public double SystemDeterminant(){
        return ((Xi1.size()*sumX2i1()*sumX2i2())+(sumaXi1()*sumXi1Xi2()
        *sumaXi2())+(sumaXi2()*sumaXi1()*sumXi1Xi2()))-((sumaXi2()*sumX2i1()
        *sumaXi2())+(sumXi1Xi2()*sumXi1Xi2()*Xi1.size())+(sumX2i2()*sumaXi1()
        *sumaXi1()));
    }
    public double sumX2i1(){
        double R=0;
        for(int it=0;it<Xi1.size();it++){
            R+=Math.pow(Xi1.get(it),2);
        } 
        return R;
    }
    public double sumX2i2(){
        double R=0;
        for(int it=0;it<Xi2.size();it++){
            R+=Math.pow(Xi2.get(it),2);
        } 
        return R;
    }
    public double sumXi1Xi2(){
       double R=0;
        for(int it=0;it<Xi1.size();it++){
            R+=Xi1.get(it)*Xi2.get(it);
        }
        return R; 
    }
    
    public double sumXi2Yi(){
        double R=0;
        for(int it=0;it<Xi2.size();it++){
            R+=Xi2.get(it)*Yi.get(it);
        }
        return R;
    }
    
    public double sumXi1Yi(){
        double R=0;
        for(int it=0;it<Xi1.size();it++){
            R+=Xi1.get(it)*Yi.get(it);
        }
        return R;
    }
    
    public double sumaXi1(){
        double R=0;
        for(int it=0;it<Xi1.size();it++){
            R+=Xi1.get(it);
        }
        return R;
    }
    
    public double sumaXi2(){
        double R=0;
        for(int it=0;it<Xi2.size();it++){
            R+=Xi2.get(it);
        }
        return R;
    }
    public double sumaYi(){
        double R=0;
        for(int it=0;it<Yi.size();it++){
            R+=Yi.get(it);
        }
        return R;
    }
}
