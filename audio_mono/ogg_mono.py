import ffmpeg
import os

directory = '..\\src\\main\\resources\\assets\\deep_aether\\sounds'

for entry in os.scandir(".\\"):
    if entry.is_file() and '.ogg' in entry.name:
        print(entry.name + " has been converted.")
        ffmpeg.input(entry.path).output(directory + "\\" + entry.name.replace('.ogg', "_mono.ogg"), ac=1, ar=44100).run(overwrite_output=True)

