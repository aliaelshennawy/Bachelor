class CreateAdvices < ActiveRecord::Migration
  def change
    create_table :advices do |t|

      t.timestamps null: false
    end
  end
end
