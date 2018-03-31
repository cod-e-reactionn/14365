<?php
$con=mysqli_connect (mysql.hostinger.in, 

u659121717_sid, mamakhan,u659121717_sid) or die ('I cannot connect 

to the database because ' . mysql_error());
mysqli_select_db ($con,'user');

echo "hi123445y67657";

if(isset($_POST['submit']))

 {
echo "hi123445y67657";
     $collegename=$_POST['collegename'];

     $fullname=$_POST['fullname'];

     $adhar=$_POST['adhar'];

     $password=$_POST['password'];

     $cpassword=$_POST['cpassword'];


     $Gender=$_POST['Gender'];

     $dob=$_POST['dob'];

     $branch=$_POST['branch'];

     $semester=$_POST['year'];


     if($password==$cpassword)
      {
echo "hi123445y67657";
 
           $query=" select * from studentregi where username='$username'";

           $query_run=mysli_query($con,$query);

           if($query_run)
           {
                     if(mysqli_num_rows($query_run)>0)

                     {
                      echo '<script type="text/javascript">alert("this username already exist please try another username")</script>';

                     }
                    else
                    {
                      $query="insert into studentregi values('$fullanme,$adhar,$password,$Gender,$branch,$semester,$year,$collegename')";
                      $query_run=mysqli_query($con,$query);
                      if(query_run)
                      {
                        echo '<script type="text/javascript">alert("user registered successfully")</script>';
                        $SESSION['username']=$username;
                        $SESSION['password']=$password;
                        header("Location:file name");

                      }
                      else
              {
                echo '<p class="bg-danger msg-block">Registration Unsuccessful due to server error. Please try later</p>';
              }
            }
          }
          else
          {
            echo '<script type="text/javascript">alert("DB error")</script>';
          }
        }
        else
        {
          echo '<script type="text/javascript">alert("Password and Confirm Password do not match")</script>';
        }
        
      }
      else
      {
      }
                    }
           }


      }

 }
 ?>