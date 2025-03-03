import pandas as pd  
from sklearn.model_selection import train_test_split  
from sklearn.linear_model import LogisticRegression  
from sklearn.metrics import accuracy_score  
  

data = {  
    'barcelona_goals': [4, 2, 1, 1, 0, 2],  
    'real_madrid_goals': [0, 3, 4, 2, 4, 1],  
    'pemenang': ['Barcelona', 'Real Madrid', 'Real Madrid', 'Real Madrid', 'Real Madrid', 'Barcelona']  
}  
   
df = pd.DataFrame(data)  
  
df['pemenang'] = df['pemenang'].map({'Barcelona': 0, 'Real Madrid': 1})  
  
df['goal_difference'] = df['barcelona_goals'] - df['real_madrid_goals']  
  

X = df[['barcelona_goals', 'real_madrid_goals', 'goal_difference']]  
y = df['pemenang']  
  

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)  
  

model = LogisticRegression(max_iter=1000)  
  

model.fit(X_train, y_train)  
  

y_pred = model.predict(X_test)  
  

final_prediction = model.predict(X_test)  
  
 
avg_barcelona_goals = df['barcelona_goals'].mean()  
avg_real_madrid_goals = df['real_madrid_goals'].mean()  
goal_difference = avg_barcelona_goals - avg_real_madrid_goals  
  
new_match = pd.DataFrame({  
    'barcelona_goals': [avg_barcelona_goals],  
    'real_madrid_goals': [avg_real_madrid_goals],  
    'goal_difference': [goal_difference]  
})  
  
prediction = model.predict(new_match)  
probabilities = model.predict_proba(new_match)[0]  
   
pred_team = "Barcelona" if prediction[0] == 0 else "Real Madrid"  
barcelona_prob = probabilities[0] * 100  
real_madrid_prob = probabilities[1] * 100  
  
print(f"\nPrediksi untuk Pertandingan Malam Ini:")  
print(f"\nTim yang diprediksi menang: {pred_team}")  
print(f"Probabilitas Barcelona menang: {barcelona_prob:.2f}%")  
print(f"Probabilitas Real Madrid menang: {real_madrid_prob:.2f}%")  
