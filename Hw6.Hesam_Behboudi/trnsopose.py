def transpose( l):
    Nodeqotreasli = l
    while ( Nodeqotreasli != None):
        rightof_Nodeqotreasli=Nodeqotreasli.right
        down_of_Nodeqotreasli=Nodeqotreasli.down
        while(rightof_Nodeqotreasli !=None):
            temp=rightof_Nodeqotreasli.data
            rightof_Nodeqotreasli.data=down_of_Nodeqotreasli.data
            down_of_Nodeqotreasli.data=temp
            rightof_Nodeqotreasli=rightof_Nodeqotreasli.right
            down_of_Nodeqotreasli=down_of_Nodeqotreasli.down
        
        if(Nodeqotreasli.right==None):
            break
        Nodeqotreasli=Nodeqotreasli.right
        if(Nodeqotreasli.down==None):
            break
        Nodeqotreasli=Nodeqotreasli.down

    




