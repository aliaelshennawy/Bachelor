class CreateUserProblems < ActiveRecord::Migration
  def change
    create_table :user_problems do |t|
      t.integer :user_a_id
      t.integer :user_b_id
      t.integer :problem_id

      t.timestamps null: false
    end
  end
end
