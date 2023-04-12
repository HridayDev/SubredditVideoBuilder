import praw
import requests
import os
import subprocess
from PIL import Image


#------------------------------------------------------------------------------------#
client_id = ""
client_secret = ""
user_agent = ""
subreddit_name = ""
img_cnt = 0
img_typ = ""

f_or_i = input("file or input: ['f', 'i']")

if f_or_i == 'f':
    print("lol")
    quit()

elif f_or_i == 'i':
    client_id = input("Client Id: ")
    client_secret = input("Client Secret: ")
    user_agent = input("User Agent: ")
    subreddit_name = input(": ")
    try:
        img_cnt = int(input("Image Count: "))
    except Exception:
        print(f"INVALID INPUT '{img_cnt}'")
        quit()
    img_typ = input("Image Type: ['hot', 'top', 'new'] ")

#------------------------------------------------------------------------------------#


# Set up a PRAW instance with your App ID, App Secret, and user credentials
# reddit = praw.Reddit(client_id='5OzR5Jf27CI1DqMbjuBNGw',
#         client_secret='2IA9Itj9vBMlc4RxGWJhzcd9Ezbjtg',
#         user_agent="meme_finder")
reddit = praw.Reddit(client_id=client_id,
        client_secret=client_secret,
        user_agent=user_agent)

# Authenticate with the Reddit API
reddit.read_only = True # or reddit.submit = True

# Get the top posts from the "ProgrammerHumor" subreddit
subreddit = reddit.subreddit(subreddit_name)


top_posts = {}
if img_typ == 'hot':
    top_posts = subreddit.hot(limit=img_cnt)
elif img_typ == 'top':
    top_posts = subreddit.top(limit=img_cnt)
elif img_typ == 'new':
    top_posts = subreddit.new(limit=img_cnt)
else:
    print(f"INVALID INPUT Image Type '{img_typ}'")
    quit()


img_fldr = input("Directory To Store Images In: ")
img_fil_ext = input("Image File Extention: ")
# Print the titles of the top posts
with open(input("Output Text File Name: "), "w", encoding="utf-8") as file:
    i = 1
    for post in top_posts:
        print(f"Procesing Image: {i}")
        file.write(f"{i}: {post.author.name} -> https://reddit.com/r/{subreddit}/comments/{post}/\n")
        with open(f"{img_fldr}\\{i}.{img_fil_ext}", "wb") as img:
            img.write(requests.get(post.url).content)
        i += 1


#------------------------------------------------------------------------------------#
#-----------------------------------IMAGE TO VIDEO-----------------------------------#
#------------------------------------------------------------------------------------#

# Get the list of image files in the image directory
image_files = [f for f in os.listdir(img_fldr) if f.endswith(img_fil_ext)]

# Sort the image files by filename
image_files.sort()

# Load the images using Pillow and create a list of image frames
image_frames = []
for image_file in image_files:
    image_path = os.path.join(img_fldr, image_file)
    image = Image.open(image_path)
    image_frames.append(image)

# Use ffmpeg to create a video from the images and audio
command = f"ffmpeg -y -framerate {fps} -f image2pipe -i - -i {audio_path} -c:v libx264 -pix_fmt yuv420p -c:a aac -strict experimental -shortest {output_file}"
"
process = subprocess.Popen(command, stdin=subprocess.PIPE)

# Write the image frames to the ffmpeg process
for image_frame in image_frames:
    image_frame.save(process.stdin, 'PNG')

# process.stdin.close()
process.wait()
